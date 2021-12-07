package br.com.bestcombo.ports.dao;

import java.math.BigDecimal;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import br.com.bestcombo.core.saldo.entity.SaldoEntity;
import br.com.bestcombo.core.saldo.entity.SaldoPK;

public interface SaldoDAO extends DAO<SaldoEntity, SaldoPK> {

    Optional<SaldoEntity> buscaUltimoSaldoPorLoja(UUID codigoLoja);

    List<SaldoEntity> buscaUltimoSaldoEntreDatas(int ano, Month mes, UUID codigoLoja);

    Map<String, BigDecimal> getCreditosDebitosPorMes(Month mes, Integer ano, UUID codigoLoja);

}
