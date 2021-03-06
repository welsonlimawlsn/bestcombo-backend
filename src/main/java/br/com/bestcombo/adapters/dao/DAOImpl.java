package br.com.bestcombo.adapters.dao;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    protected EntityManager entityManager;

    @Override
    public void persiste(ENTIDADE entidade) {
        entityManager.persist(entidade);
    }

    @Override
    public Optional<ENTIDADE> buscaPorId(CHAVE_PRIMARIA id) {
        ENTIDADE entidade = entityManager.find(entidadeClass, id);

        return Optional.ofNullable(entidade);
    }

    @Override
    public Collection<ENTIDADE> lista(Map<String, Object> queryParameters) {
        StringBuilder sql = new StringBuilder(getSqlListar());

        for (String atributo : queryParameters.keySet()) {
            sql.append(" AND e.")
                    .append(atributo)
                    .append(" = :")
                    .append(atributo.replace(".", ""));
        }

        TypedQuery<ENTIDADE> query = entityManager.createQuery(sql.toString(), entidadeClass);

        queryParameters
                .forEach((atributo, valor) -> query.setParameter(atributo.replace(".", ""), valor));

        return new HashSet<>(query.getResultList());
    }

    @Override
    public Collection<ENTIDADE> lista() {
        return lista(new HashMap<>());
    }

    protected Optional<ENTIDADE> getResultadoUnico(TypedQuery<ENTIDADE> query) {
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    protected String getSqlListar() {
        return "SELECT e FROM " + entidadeClass.getSimpleName() + " e WHERE 1 = 1";
    }

}
