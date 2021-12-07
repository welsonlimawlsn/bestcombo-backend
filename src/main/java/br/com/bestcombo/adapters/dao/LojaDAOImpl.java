package br.com.bestcombo.adapters.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.loja.entity.LojaEntity_;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity_;
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

    @Override
    public Collection<LojaEntity> buscaLojaPorTermo(String termo) {
        TypedQuery<LojaEntity> query = entityManager.createNamedQuery("buscaLojaPorTermo", LojaEntity.class);

        query.setParameter("termo", "%" + termo.toLowerCase() + "%");

        return new HashSet<>(query.getResultList());
    }

    @Override
    public Collection<LojaEntity> listaUltimasLojas() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<LojaEntity> query = cb.createQuery(LojaEntity.class);
        Root<LojaEntity> loja = query.from(LojaEntity.class);
        query.select(loja);

        loja.fetch(LojaEntity_.produtos);

        Subquery<UUID> subquery = query.subquery(UUID.class);
        Root<LojaEntity> codigosLojas = subquery.from(LojaEntity.class);
        subquery.select(codigosLojas.get(LojaEntity_.codigo));
        codigosLojas.join(LojaEntity_.produtos);

        query.where(loja.get(LojaEntity_.codigo).in(subquery));

        TypedQuery<LojaEntity> typedQuery = entityManager.createQuery(query);

        return new HashSet<>(typedQuery.getResultList());
    }

}
