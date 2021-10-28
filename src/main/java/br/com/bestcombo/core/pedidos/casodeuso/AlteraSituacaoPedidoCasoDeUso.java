package br.com.bestcombo.core.pedidos.casodeuso;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.AlteraSituacaoPedidoRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.AlteraSituacaoPedidoRespostaDTO;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.ports.dao.PedidoDAO;
import br.com.bestcombo.ports.service.PagamentoService;
import br.com.bestcombo.ports.service.SegurancaService;

@ApplicationScoped
@CasoDeUso(CasosDeUso.ALTERA_SITUACAO_PEDIDO)
public class AlteraSituacaoPedidoCasoDeUso extends AbstractCasoDeUso<AlteraSituacaoPedidoRequisicaoDTO, AlteraSituacaoPedidoRespostaDTO> {

    @Inject
    PedidoDAO pedidoDAO;

    @Inject
    SegurancaService segurancaService;

    @Inject
    PagamentoService pagamentoService;

    @Override
    protected void processa(AlteraSituacaoPedidoRequisicaoDTO requisicao, AlteraSituacaoPedidoRespostaDTO resposta) throws NegocioException {
        PedidoEntity pedidoEntity = pedidoDAO.buscaPorIdECodigoParceiro(requisicao.getCodigoPedido(), segurancaService.getCodigoUsuarioLogado())
                .orElseThrow(() -> new NegocioException(Erro.PEDIDO_NAO_ENCONTRADO));

        switch (requisicao.getRespostaParceiro()) {
            case "PARCEIRO_ACEITA":
                pedidoEntity.aceita();
                pagamentoService.processaPagamento(pedidoEntity);
                break;
            case "PARCEIRO_RECUSA":
                pedidoEntity.cancela(requisicao.getMotivoCancelamento());
                break;
            case "PARCEIRO_CONCLUI":
                pedidoEntity.conclui();
                break;
        }

    }

}
