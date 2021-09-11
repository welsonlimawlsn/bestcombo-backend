package br.com.bestcombo.adapters.dao;

import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.ports.dao.LojaDAO;

@ApplicationScoped
public class LojaDAOImpl extends DAOImpl<LojaEntity, UUID> implements LojaDAO {

    public LojaDAOImpl() {
        super(LojaEntity.class);
    }

    @Override
    public Optional<LojaEntity> buscaLojaPorParceiro(UUID codigoParceiro) {
        TypedQuery<LojaEntity> query = entityManager.createNamedQuery("buscaLojaPorParceiro", LojaEntity.class);

        query.setParameter("codigoParceiro", codigoParceiro);

        return getResultadoUnico(query);
    }

    @Override
    public Optional<LojaEntity> buscaLojaPorCNPJ(String cnpj) {
        TypedQuery<LojaEntity> query = entityManager.createNamedQuery("buscaLojaPorCNPJ", LojaEntity.class);

        query.setParameter("cnpj", cnpj);

        return getResultadoUnico(query);
    }

    @Override
    public Optional<LojaEntity> buscaLojaPorCodigoEParceiro(UUID codigoLoja, UUID codigoParceiro) {
        TypedQuery<LojaEntity> query = entityManager.createNamedQuery("buscaLojaPorCodigoEParceiro", LojaEntity.class);

        query.setParameter("codigo", codigoLoja);
        query.setParameter("codigoParceiro", codigoParceiro);
        return getResultadoUnico(query);
    }

}
