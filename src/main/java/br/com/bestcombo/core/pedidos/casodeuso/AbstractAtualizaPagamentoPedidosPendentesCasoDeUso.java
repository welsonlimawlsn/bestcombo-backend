package br.com.bestcombo.core.pedidos.casodeuso;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.CancelaPedidoMailTemplate;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.PagamentoAprovadoMailTemplate;
import br.com.bestcombo.core.pedidos.dto.atualizapagamento.AtualizaPagamentoPedidosPendentesRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.atualizapagamento.AtualizaPagamentoPedidosPendentesRespostaDTO;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedido;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.ports.service.EmailService;
import br.com.bestcombo.ports.service.MovimentoService;
import br.com.bestcombo.ports.service.PagamentoService;

public abstract class AbstractAtualizaPagamentoPedidosPendentesCasoDeUso<REQ extends AtualizaPagamentoPedidosPendentesRequisicaoDTO<RES>, RES extends AtualizaPagamentoPedidosPendentesRespostaDTO> extends AbstractCasoDeUso<REQ, RES> {

    @Inject
    PagamentoService pagamentoService;

    @Inject
    MovimentoService movimentoService;

    @Inject
    EmailService emailService;

    @Override
    protected void processa(REQ req, RES res) throws NegocioException {
        Collection<PedidoEntity> pedidosPendentes = getPedidosPendentes();

        if (pedidosPendentes.size() > 0) {
            Map<UUID, SituacaoPedido> situacaoPedidos = pagamentoService.getSituacaoPedidos(pedidosPendentes);

            for (var pedido : pedidosPendentes) {
                SituacaoPedido situacaoPedido = situacaoPedidos.get(pedido.getCodigo());
                if (Objects.nonNull(situacaoPedido)) {
                    pedido.setSituacao(situacaoPedido);

                    if (SituacaoPedido.PARCEIRO_PREPARANDO_PEDIDO.equals(situacaoPedido)) {
                        movimentoService.criaMovimentoPedido(pedido);
                        enviaEmailPagamentoAprovado(pedido, pedido.getLoja().getParceiro(), pedido.getLoja().getNome());
                        enviaEmailPagamentoAprovado(pedido, pedido.getCliente(), pedido.getCliente().getNome());
                    }

                    if (SituacaoPedido.PEDIDO_CANCELADO.equals(situacaoPedido)) {
                        pedido.setMotivoCancelamento("Pedido não aprovado pelo cartão de crédito");
                        enviaEmailCancelamento(pedido, pedido.getLoja().getParceiro(), pedido.getLoja().getNome());
                        enviaEmailCancelamento(pedido, pedido.getCliente(), pedido.getCliente().getNome());
                    }
                }
            }

            atualizaQuantidadePedidosAtualzados(res, situacaoPedidos.size());
        } else {
            atualizaQuantidadePedidosAtualzados(res, 0);
        }

    }

    private void enviaEmailPagamentoAprovado(PedidoEntity pedido, PessoaEntity parceiro, String nome) {
        emailService.sendMail(PagamentoAprovadoMailTemplate.builder()
                .to(parceiro.getEmail())
                .subject("Pagamento de pedido aprovado")
                .nomeCliente(nome)
                .codigoPedido(pedido.getCodigo().toString())
                .build());
    }

    private void enviaEmailCancelamento(PedidoEntity pedido, PessoaEntity parceiro, String nome) {
        emailService.sendMail(CancelaPedidoMailTemplate.builder()
                .to(parceiro.getEmail())
                .subject("Pedido cancelado")
                .codigoPedido(pedido.getCodigo().toString())
                .nomeUsuario(nome)
                .motivoCancelamento(pedido.getMotivoCancelamento())
                .build());
    }

    private void atualizaQuantidadePedidosAtualzados(RES res, int size) {
        res.setQuantidadeRegistrosAtualizados(size);
    }

    protected abstract Collection<PedidoEntity> getPedidosPendentes();

}
