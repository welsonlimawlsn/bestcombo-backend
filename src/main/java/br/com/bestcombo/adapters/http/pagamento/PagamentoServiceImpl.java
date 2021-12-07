package br.com.bestcombo.adapters.http.pagamento;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.adapters.http.client.pagarme.PagarMeClient;
import br.com.bestcombo.adapters.http.client.pagarme.dao.PagarMeDAO;
import br.com.bestcombo.adapters.http.client.pagarme.dto.RequisicaoPagarMe;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao.Cliente;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao.DadosCobranca;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao.Documento;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao.Endereco;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao.NovaTransacaoRequisicao;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao.NovaTransacaoResposta;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao.Produto;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novocartao.NovoCartaoRequisicao;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novocartao.NovoCartaoResposta;
import br.com.bestcombo.adapters.http.client.pagarme.entity.PagarMeEntity;
import br.com.bestcombo.core.enderecos.entity.EnderecoPessoaEntity;
import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.core.pedidos.dto.novopedido.CartaoDTO;
import br.com.bestcombo.core.pedidos.dto.novopedido.NovoPedidoRequisicaoDTO;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedido;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.ports.service.PagamentoService;

@Slf4j
@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    public static final String PAIS_ORIGEM = "br";

    private static final Map<String, SituacaoPedido> mapperSituacaoPedido;

    @ConfigProperty(name = "pagamento.api-key")
    String pagarmeApiKey;

    static {
        mapperSituacaoPedido = new HashMap<>();

        mapperSituacaoPedido.put("paid", SituacaoPedido.PARCEIRO_PREPARANDO_PEDIDO);
        mapperSituacaoPedido.put("refused", SituacaoPedido.PEDIDO_CANCELADO);
    }

    @Inject
    @RestClient
    PagarMeClient pagarMeClient;

    @Inject
    PagarMeDAO pagarMeDAO;

    @Override
    public void processaPagamento(PedidoEntity pedido) {
        try {
            PagarMeEntity pagarMe = pagarMeDAO.buscaPorId(pedido.getCodigo()).orElseThrow();
            NovaTransacaoResposta resposta = pagarMeClient.novaTransancao(criaRequisicaoNovaTransacao(pedido, pagarMe));
            pagarMe.setCodigoTransacao(resposta.getCodigoTransancao());
        } catch (ResteasyWebApplicationException e) {
            throw new InfraestruturaException(String.format("Ocorreu um erro ao cria transacao de pagamento: %s", getResponseError(e)), e);
        }
    }

    private String getResponseError(ResteasyWebApplicationException e) {
        return new String(((ByteArrayInputStream) e.unwrap().getResponse().getEntity()).readAllBytes());
    }

    private NovaTransacaoRequisicao criaRequisicaoNovaTransacao(PedidoEntity pedido, PagarMeEntity pagarMe) {
        PessoaEntity cliente = pedido.getCliente();
        EnderecoPessoaEntity enderecoCliente = cliente.getEnderecos().stream().findFirst().orElseThrow();
        String nomeCliente = cliente.getNome() + " " + cliente.getSobrenome();
        NovaTransacaoRequisicao requisicao = NovaTransacaoRequisicao.builder()
                .valorCobranca(converteBigDecimalParaLong(pedido.getValorTotal()))
                .codigoCartao(pagarMe.getCodigoCartao())
                .dadosCobranca(DadosCobranca.builder()
                        .nomePessoa(nomeCliente)
                        .endereco(Endereco.builder()
                                .pais("br")
                                .estado(enderecoCliente.getEstado())
                                .cidade(enderecoCliente.getCidade())
                                .cep(enderecoCliente.getCep().replace("-", ""))
                                .rua(enderecoCliente.getRua())
                                .numero(enderecoCliente.getNumero())
                                .build())
                        .build())
                .cliente(Cliente.builder()
                        .codigo(cliente.getCodigo().toString())
                        .email(cliente.getEmail())
                        .pais(PAIS_ORIGEM)
                        .telefones(List.of("+55" + cliente.getTelefone()))
                        .tipo("individual")
                        .nome(nomeCliente)
                        .documentos(List.of(Documento.builder()
                                .numero(cliente.getCpf())
                                .tipo("cpf")
                                .build()))
                        .build())
                .produtos(pedido.getProdutos().stream().map(produtoPedido -> Produto.builder()
                        .produtoFisico(true)
                        .quantidade(produtoPedido.getQuantidade())
                        .valorUnitario(converteBigDecimalParaLong(produtoPedido.getValorUnitario()))
                        .codigo(produtoPedido.getProduto().getCodigo().toString())
                        .nome(produtoPedido.getProduto().getNome())
                        .build()).collect(Collectors.toList()))
                .build();

        configuraRequisicao(requisicao);

        return requisicao;
    }

    private long converteBigDecimalParaLong(BigDecimal valor) {
        return Long.parseLong(valor.setScale(2, RoundingMode.HALF_EVEN).toString().replace(".", ""));
    }

    @Override
    public void preparaPagamento(PedidoEntity pedido, NovoPedidoRequisicaoDTO requisicao) {
        NovoCartaoResposta resposta = pagarMeClient.novoCartao(criaRequisicaoNovoCartao(requisicao));
        pagarMeDAO.persiste(PagarMeEntity.builder()
                .codigoPedido(pedido.getCodigo())
                .codigoCartao(resposta.getCodigo())
                .build());
    }

    @Override
    public Map<UUID, SituacaoPedido> getSituacaoPedidos(Collection<PedidoEntity> pedidosPendentes) {
        Set<UUID> codigosPedidosPendentes = pedidosPendentes.stream()
                .map(PedidoEntity::getCodigo)
                .collect(Collectors.toSet());

        Collection<PagarMeEntity> entities = pagarMeDAO.buscaPorIds(codigosPedidosPendentes);

        RequisicaoPagarMe requisicao = criaRequisicaoGenerica();

        Map<PagarMeEntity, NovaTransacaoResposta> transacoes = entities.parallelStream()
                .collect(Collectors.toMap(p -> p, p -> pagarMeClient.buscaTransacao(p.getCodigoTransacao(), requisicao)));

        Map<UUID, SituacaoPedido> pedidosAtualizados = new HashMap<>();

        transacoes.forEach((entity, resposta) -> {
            SituacaoPedido situacaoPedido = mapperSituacaoPedido.get(resposta.getSituacaoTransancao());
            if (Objects.nonNull(situacaoPedido)) {
                pedidosAtualizados.put(entity.getCodigoPedido(), situacaoPedido);
            }
        });

        return pedidosAtualizados;
    }

    private RequisicaoPagarMe criaRequisicaoGenerica() {
        RequisicaoPagarMe requisicao = new RequisicaoPagarMe();

        configuraRequisicao(requisicao);

        return requisicao;
    }

    private NovoCartaoRequisicao criaRequisicaoNovoCartao(NovoPedidoRequisicaoDTO requisicao) {
        CartaoDTO cartao = requisicao.getCartao();

        NovoCartaoRequisicao r = NovoCartaoRequisicao.builder()
                .cvv(cartao.getCvv())
                .dataVencimento(cartao.getDataVencimento())
                .nome(cartao.getNome())
                .numero(cartao.getNumero())
                .build();

        configuraRequisicao(r);

        return r;
    }

    private void configuraRequisicao(RequisicaoPagarMe requisicao) {
        requisicao.setChaveApi(pagarmeApiKey);
    }

}
