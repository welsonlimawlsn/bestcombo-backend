package br.com.bestcombo.adapters.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;
import br.com.bestcombo.core.solicitacaosaque.enums.SituacaoSolicitacaoSaque;
import br.com.bestcombo.ports.dao.SolicitacaoSaqueDAO;

@ApplicationScoped
public class SolicitacaoSaqueDAOImpl extends DAOImpl<SolicitacaoSaqueEntity, UUID> implements SolicitacaoSaqueDAO {

    public SolicitacaoSaqueDAOImpl() {
        super(SolicitacaoSaqueEntity.class);
    }

    @Override
    public Optional<SolicitacaoSaqueEntity> buscaSolicitacaoPendentePorLoja(LojaEntity lojaEntity) {
        TypedQuery<SolicitacaoSaqueEntity> query = entityManager.createQuery("SELECT s FROM SolicitacaoSaqueEntity s WHERE s.loja = :loja AND s.situacaoSolicitacaoSaque IN (:situacoesPendentes)", SolicitacaoSaqueEntity.class);

        query.setParameter("loja", lojaEntity);
        query.setParameter("situacoesPendentes", Arrays.asList(SituacaoSolicitacaoSaque.SOLICITADO, SituacaoSolicitacaoSaque.EM_ANDAMENTO));

        return getResultadoUnico(query);
    }

    @Override
    public Collection<SolicitacaoSaqueEntity> listaPendentes() {
        TypedQuery<SolicitacaoSaqueEntity> query = entityManager.createQuery("SELECT s FROM SolicitacaoSaqueEntity s WHERE s.situacaoSolicitacaoSaque IN (:situacoesPendentes)", SolicitacaoSaqueEntity.class);

        query.setParameter("situacoesPendentes", Arrays.asList(SituacaoSolicitacaoSaque.EM_ANDAMENTO, SituacaoSolicitacaoSaque.SOLICITADO));

        return query.getResultList();
    }

}
