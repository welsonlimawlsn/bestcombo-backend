package br.com.bestcombo.core.pedidos.casodeuso;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pedidos.dto.atualizapagamento.AtualizaPagamentoPedidosPendentesRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.atualizapagamento.AtualizaPagamentoPedidosPendentesRespostaDTO;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedido;
import br.com.bestcombo.ports.service.MovimentoService;
import br.com.bestcombo.ports.service.PagamentoService;

public abstract class AbstractAtualizaPagamentoPedidosPendentesCasoDeUso<REQ extends AtualizaPagamentoPedidosPendentesRequisicaoDTO<RES>, RES extends AtualizaPagamentoPedidosPendentesRespostaDTO> extends AbstractCasoDeUso<REQ, RES> {

    @Inject
    PagamentoService pagamentoService;

    @Inject
    MovimentoService movimentoService;

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
                    }

                    if (SituacaoPedido.PEDIDO_CANCELADO.equals(situacaoPedido)) {
                        pedido.setMotivoCancelamento("Pedido não aprovado pelo cartão de crédito");
                    }
                }
            }

            atualizaQuantidadePedidosAtualzados(res, situacaoPedidos.size());
        } else {
            atualizaQuantidadePedidosAtualzados(res, 0);
        }

    }

    private void atualizaQuantidadePedidosAtualzados(RES res, int size) {
        res.setQuantidadeRegistrosAtualizados(size);
    }

    protected abstract Collection<PedidoEntity> getPedidosPendentes();

}
