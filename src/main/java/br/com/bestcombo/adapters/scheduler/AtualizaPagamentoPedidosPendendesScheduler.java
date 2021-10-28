package br.com.bestcombo.adapters.scheduler;

import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pedidos.dto.atualizapagamento.AtualizaPagamentoTodosPedidosPendentesRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.atualizapagamento.AtualizaPagamentoTodosPedidosPendentesRespostaDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Slf4j
@ApplicationScoped
public class AtualizaPagamentoPedidosPendendesScheduler {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @Scheduled(every = "5m")
    public void atualizaPagamentoPedidosPendendes() throws NegocioException {
        log.info("Iniciando a atualização de pagamentos de pedidos pendentes");

        AtualizaPagamentoTodosPedidosPendentesRespostaDTO resposta = processadorCasoDeUso.processa(new AtualizaPagamentoTodosPedidosPendentesRequisicaoDTO());

        log.info("Finalizando a atualização de pagamentos. Foram atualizados {} pedidos", resposta.getQuantidadeRegistrosAtualizados());
    }

}
