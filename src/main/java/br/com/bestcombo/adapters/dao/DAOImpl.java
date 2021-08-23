package br.com.bestcombo.adapters.dao;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.bestcombo.ports.dao.DAO;

@RequiredArgsConstructor
public class DAOImpl<ENTIDADE, CHAVE_PRIMARIA extends Serializable> implements DAO<ENTIDADE, CHAVE_PRIMARIA> {

    private final Class<ENTIDADE> entidadeClass;

    @Inject
    EntityManager entityManager;

    @Override
    public void persiste(ENTIDADE entidade) {
        entityManager.persist(entidade);
    }

    @Override
    public Optional<ENTIDADE> buscaPorId(CHAVE_PRIMARIA id) {
        ENTIDADE entidade = entityManager.find(entidadeClass, id);

        return Optional.ofNullable(entidade);
    }

    protected Optional<ENTIDADE> getResultadoUnico(TypedQuery<ENTIDADE> query) {
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
