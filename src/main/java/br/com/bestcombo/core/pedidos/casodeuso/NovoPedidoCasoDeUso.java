package br.com.bestcombo.core.pedidos.casodeuso;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.pedidos.dto.novopedido.NovoPedidoMailTemplate;
import br.com.bestcombo.core.pedidos.dto.novopedido.NovoPedidoRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.novopedido.NovoPedidoRespostaDTO;
import br.com.bestcombo.core.pedidos.dto.novopedido.ProdutoPedidoDTO;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.pedidos.entity.ProdutoPedidoEntity;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.ports.dao.PedidoDAO;
import br.com.bestcombo.ports.dao.PessoaDAO;
import br.com.bestcombo.ports.dao.ProdutoDAO;
import br.com.bestcombo.ports.service.EmailService;
import br.com.bestcombo.ports.service.PagamentoService;
import br.com.bestcombo.ports.service.SegurancaService;

@CasoDeUso(CasosDeUso.NOVO_PEDIDO)
@ApplicationScoped
public class NovoPedidoCasoDeUso extends AbstractCasoDeUso<NovoPedidoRequisicaoDTO, NovoPedidoRespostaDTO> {

    @Inject
    SegurancaService segurancaService;

    @Inject
    ProdutoDAO produtoDao;

    @Inject
    PedidoDAO pedidoDAO;

    @Inject
    PagamentoService pagamentoService;

    @Inject
    EmailService emailService;

    @Inject
    PessoaDAO pessoaDAO;

    @Override
    protected void processa(NovoPedidoRequisicaoDTO requisicao, NovoPedidoRespostaDTO resposta) throws NegocioException {
        PessoaEntity usuarioLogado = segurancaService.getUsuarioLogado();

        LojaEntity loja = mapperParaLojaEntity(requisicao);

        Map<UUID, Integer> produtosQuantidade = mapperParaProdutoEntity(requisicao.getProdutos());

        Collection<ProdutoEntity> produtos = produtoDao.buscaPorIdsECodigoLoja(produtosQuantidade.keySet(), loja);

        PedidoEntity pedido = criaPedido(requisicao, usuarioLogado, loja);

        Set<ProdutoPedidoEntity> produtosPedidos = criaProdutosPedido(produtosQuantidade, produtos, pedido);

        pedido.setProdutos(produtosPedidos);

        pedidoDAO.persiste(pedido);

        pagamentoService.preparaPagamento(pedido, requisicao);

        resposta.setCodigoPedido(pedido.getCodigo());

        enviaEmail(loja, pedido);
    }

    private void enviaEmail(LojaEntity loja, PedidoEntity pedido) {

        PessoaEntity pessoaEntity = pessoaDAO.buscaParceiroPorLoja(loja).orElseThrow();

        emailService.sendMail(NovoPedidoMailTemplate.builder()
                        .to(pessoaEntity.getEmail())
                        .subject("Novo Pedido")
                        .codigoPedido(pedido.getCodigo().toString())
                        .nomeParceiro(pessoaEntity.getNome())
                .build());
    }

    private PedidoEntity criaPedido(NovoPedidoRequisicaoDTO requisicao, PessoaEntity usuarioLogado, LojaEntity loja) {
        return PedidoEntity.builder()
                .cliente(usuarioLogado)
                .dataHoraAgendamento(requisicao.getDataAgendamento())
                .loja(loja)
                .observacao(requisicao.getObservacao())
                .build();
    }

    private Set<ProdutoPedidoEntity> criaProdutosPedido(Map<UUID, Integer> produtosQuantidade, Collection<ProdutoEntity> produtos, PedidoEntity pedido) {
        return produtos.stream().map(entity -> ProdutoPedidoEntity.builder()
                .produto(entity)
                .valorUnitario(entity.getPreco())
                .quantidade(produtosQuantidade.get(entity.getCodigo()))
                .pedido(pedido)
                .build()).collect(Collectors.toSet());
    }

    private LojaEntity mapperParaLojaEntity(NovoPedidoRequisicaoDTO requisicao) {
        return LojaEntity.builder()
                .codigo(requisicao.getCodigoEstabelecimento())
                .build();
    }

    private Map<UUID, Integer> mapperParaProdutoEntity(List<ProdutoPedidoDTO> produtos) {
        return produtos.stream()
                .collect(Collectors.toMap(ProdutoPedidoDTO::getCodigo, ProdutoPedidoDTO::getQuantidade));
    }

}
