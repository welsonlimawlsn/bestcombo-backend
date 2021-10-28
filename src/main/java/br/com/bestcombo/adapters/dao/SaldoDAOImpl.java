package br.com.bestcombo.adapters.dao;

import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.saldo.entity.SaldoEntity;
import br.com.bestcombo.core.saldo.entity.SaldoPK;
import br.com.bestcombo.ports.dao.SaldoDAO;

@ApplicationScoped
public class SaldoDAOImpl extends DAOImpl<SaldoEntity, SaldoPK> implements SaldoDAO {

    public SaldoDAOImpl() {
        super(SaldoEntity.class);
    }

    @Override
    public Optional<SaldoEntity> buscaUltimoSaldoPorLoja(UUID codigoLoja) {

        TypedQuery<SaldoEntity> query = entityManager.createQuery("SELECT s FROM SaldoEntity s WHERE s.codigoLoja = :codigoLoja ORDER BY s.dataSaldo DESC", SaldoEntity.class);

        query.setMaxResults(1);

        query.setParameter("codigoLoja", codigoLoja);

        return getResultadoUnico(query);
    }

}
