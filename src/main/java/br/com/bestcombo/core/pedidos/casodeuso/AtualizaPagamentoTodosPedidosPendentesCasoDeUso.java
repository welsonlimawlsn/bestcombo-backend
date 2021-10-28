package br.com.bestcombo.core.pedidos.casodeuso;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.pedidos.dto.atualizapagamento.AtualizaPagamentoTodosPedidosPendentesRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.atualizapagamento.AtualizaPagamentoTodosPedidosPendentesRespostaDTO;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.ports.dao.PedidoDAO;

@ApplicationScoped
@CasoDeUso(CasosDeUso.ATUALIZA_PAGAMENTO_TODOS_PEDIDOS)
public class AtualizaPagamentoTodosPedidosPendentesCasoDeUso extends AbstractAtualizaPagamentoPedidosPendentesCasoDeUso<AtualizaPagamentoTodosPedidosPendentesRequisicaoDTO, AtualizaPagamentoTodosPedidosPendentesRespostaDTO> {

    @Inject
    PedidoDAO pedidoDAO;

    @Override
    protected Collection<PedidoEntity> getPedidosPendentes() {
        return pedidoDAO.buscaPendentesPagamento();
    }

}
