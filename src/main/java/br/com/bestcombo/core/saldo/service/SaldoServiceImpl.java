package br.com.bestcombo.core.saldo.service;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.movimentoconta.entity.MovimentoContaEntity;
import br.com.bestcombo.core.saldo.entity.SaldoEntity;
import br.com.bestcombo.core.saldo.entity.SaldoPK;
import br.com.bestcombo.ports.dao.SaldoDAO;
import br.com.bestcombo.ports.service.SaldoService;

@ApplicationScoped
@RequiredArgsConstructor
public class SaldoServiceImpl implements SaldoService {

    final SaldoDAO saldoDAO;

    @Override
    public void atualizaSaldo(MovimentoContaEntity movimentoConta) throws NegocioException {
        if (movimentoConta.getEfetivado()) {
            throw new NegocioException(Erro.MOVIMENTO_JA_EFETIVADO);
        }

        boolean isDisponivelNaHora = movimentoConta.getDataHora().equals(movimentoConta.getDataHoraEfetivacao());
        boolean dataEfetivacaoPassou = movimentoConta.getDataHoraEfetivacao().isBefore(ZonedDateTime.now(ZoneOffset.UTC));

        SaldoEntity saldoDia = getSaldoDia(movimentoConta.getLoja().getCodigo());

        if (isDisponivelNaHora || dataEfetivacaoPassou) {
            alteraSaldoDiponivel(saldoDia, movimentoConta);

            if (dataEfetivacaoPassou && !isDisponivelNaHora ) {
                if (movimentoConta.isCredito()) {
                    removeSaldoFuturo(saldoDia, movimentoConta);
                }
                if (movimentoConta.isDebito()) {
                    reembolsaDebitoSaldoFuturo(saldoDia, movimentoConta);
                }
            }

            movimentoConta.efetiva();

        } else {
            adicionaSaldoFuturo(saldoDia, movimentoConta);
        }
    }

    private void reembolsaDebitoSaldoFuturo(SaldoEntity saldoDia, MovimentoContaEntity movimentoConta) {
        saldoDia.creditaSaldoFuturo(movimentoConta.getValor());
    }

    private void adicionaSaldoFuturo(SaldoEntity saldoDia, MovimentoContaEntity movimentoConta) throws NegocioException {
        if (movimentoConta.isDebito()) {
            saldoDia.debitaSaldoFuturo(movimentoConta.getValor());
        }

        if (movimentoConta.isCredito() && !movimentoConta.getDataHora().equals(movimentoConta.getDataHoraEfetivacao())) {
            saldoDia.creditaSaldoFuturo(movimentoConta.getValor());
        }
    }

    @Override
    public SaldoEntity getSaldoDia(UUID codigoLoja) {
        return saldoDAO.buscaPorId(SaldoPK.builder()
                        .dataSaldo(LocalDate.now())
                        .codigoLoja(codigoLoja)
                        .build())
                .orElseGet(() -> criaNovoSaldo(codigoLoja));
    }

    @Override
    public BigDecimal getSaldoMes(Month mes, Integer ano, UUID codigoLoja) {

        List<SaldoEntity> saldos = saldoDAO.buscaUltimoSaldoEntreDatas(ano, mes, codigoLoja);

        return saldos.stream()
                .map(SaldoEntity::getValorDisponivel)
                .reduce(BigDecimal.ZERO, BigDecimal::subtract)
                .abs();
    }

    public Map<String, BigDecimal> getCreditosDebitosPorMes(Month mes, Integer ano, UUID codigoLoja) {
        return saldoDAO.getCreditosDebitosPorMes(mes, ano, codigoLoja);
    }

    private SaldoEntity criaNovoSaldo(UUID codigoLoja) {
        Optional<SaldoEntity> saldo = saldoDAO.buscaUltimoSaldoPorLoja(codigoLoja);

        SaldoEntity novoSaldo;

        if (saldo.isPresent()) {
            novoSaldo = saldo.get().copiaComDataAtual();
        } else {
            novoSaldo = criaNovoSaldoZerado(codigoLoja);
        }

        saldoDAO.persiste(novoSaldo);

        return novoSaldo;
    }

    private SaldoEntity criaNovoSaldoZerado(UUID codigoLoja) {
        return SaldoEntity.builder()
                .codigoLoja(codigoLoja)
                .dataSaldo(LocalDate.now())
                .valorFuturo(BigDecimal.ZERO)
                .valorDisponivel(BigDecimal.ZERO)
                .build();
    }

    private void removeSaldoFuturo(SaldoEntity saldoDia, MovimentoContaEntity movimentoConta) {
        saldoDia.debitaSaldoFuturo(movimentoConta.getValor());
    }

    private void alteraSaldoDiponivel(SaldoEntity saldoEntity, MovimentoContaEntity movimentoConta) {
        if (movimentoConta.isDebito()) {
            saldoEntity.debitaSaldoDisponivel(movimentoConta.getValor());
        }

        if (movimentoConta.isCredito()) {
            saldoEntity.creditaSaldoDisponivel(movimentoConta.getValor());
        }
    }

}
