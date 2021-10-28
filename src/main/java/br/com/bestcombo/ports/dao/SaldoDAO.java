package br.com.bestcombo.ports.dao;

import java.util.Optional;
import java.util.UUID;

import br.com.bestcombo.core.saldo.entity.SaldoEntity;
import br.com.bestcombo.core.saldo.entity.SaldoPK;

public interface SaldoDAO extends DAO<SaldoEntity, SaldoPK> {

    Optional<SaldoEntity> buscaUltimoSaldoPorLoja(UUID codigoLoja);

}
