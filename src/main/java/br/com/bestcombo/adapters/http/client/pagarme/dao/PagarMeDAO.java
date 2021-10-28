package br.com.bestcombo.adapters.http.client.pagarme.dao;

import java.util.Collection;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.adapters.dao.DAOImpl;
import br.com.bestcombo.adapters.http.client.pagarme.entity.PagarMeEntity;

@ApplicationScoped
public class PagarMeDAO extends DAOImpl<PagarMeEntity, UUID> {

    public PagarMeDAO() {
        super(PagarMeEntity.class);
    }

    public Collection<PagarMeEntity> buscaPorIds(Collection<UUID> codigos) {
        TypedQuery<PagarMeEntity> query = entityManager.createQuery("SELECT p FROM PagarMeEntity p WHERE p.codigoPedido IN (:codigosPedidos)", PagarMeEntity.class);

        query.setParameter("codigosPedidos", codigos);

        return query.getResultList();
    }

}
