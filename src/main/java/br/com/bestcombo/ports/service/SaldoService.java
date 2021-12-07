package br.com.bestcombo.ports.service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;
import java.util.UUID;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.movimentoconta.entity.MovimentoContaEntity;
import br.com.bestcombo.core.saldo.entity.SaldoEntity;

public interface SaldoService {

    void atualizaSaldo(MovimentoContaEntity movimentoConta) throws NegocioException;

    SaldoEntity getSaldoDia(UUID codigoLoja);

    BigDecimal getSaldoMes(Month mes, Integer ano, UUID codigoLoja);

    Map<String, BigDecimal> getCreditosDebitosPorMes(Month mes, Integer ano, UUID codigoLoja);

}
