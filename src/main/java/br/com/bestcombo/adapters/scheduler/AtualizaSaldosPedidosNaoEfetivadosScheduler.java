package br.com.bestcombo.adapters.scheduler;

import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.service.MovimentoService;

@Slf4j
@ApplicationScoped
public class AtualizaSaldosPedidosNaoEfetivadosScheduler {

    @Inject
    MovimentoService movimentoService;

    @Scheduled(every = "5m")
    public void atualizaSaldosPedidosNaoEfetivados() throws NegocioException {
        log.info("Iniciando a atualização de saldos de pedidos não efetivados");

        int quantidadeRegistrosAtualizados = movimentoService.atualizaMovimentosNaoEfetidos();

        log.info("Finalizando a atualização de saldos de pedidos não efetivados. Foram atualizados saldos de {} movimentos", quantidadeRegistrosAtualizados);
    }

}
