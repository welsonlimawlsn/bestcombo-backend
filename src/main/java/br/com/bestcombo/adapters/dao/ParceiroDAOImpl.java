package br.com.bestcombo.adapters.dao;

import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.parceiros.entidade.ParceiroEntity;
import br.com.bestcombo.ports.dao.ParceiroDAO;

@ApplicationScoped
public class ParceiroDAOImpl extends DAOImpl<ParceiroEntity, UUID> implements ParceiroDAO {

    public ParceiroDAOImpl() {
        super(ParceiroEntity.class);
    }

    @Override
    public Optional<ParceiroEntity> buscaPorCpfCnpjOuEmail(String cpfCnpj, String email) {
        TypedQuery<ParceiroEntity> query = entityManager.createNamedQuery("buscaPorCpfCnpjOuEmail", ParceiroEntity.class);

        query.setParameter("cpfCnpj", cpfCnpj);
        query.setParameter("email", email);

        return getResultadoUnico(query);
    }

}
