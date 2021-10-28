package br.com.bestcombo.adapters.dao;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.ports.dao.PessoaDAO;

@ApplicationScoped
public class PessoaDAOImpl extends DAOImpl<PessoaEntity, UUID> implements PessoaDAO {

    public PessoaDAOImpl() {
        super(PessoaEntity.class);
    }

    @Override
    public Optional<PessoaEntity> buscaPorCpfOuEmailOuUsuario(String cpf, String email, String usuario, Integer tipoPessoa) {
        TypedQuery<PessoaEntity> query = entityManager.createNamedQuery("buscaPorCpfOuEmailOuUsuario", PessoaEntity.class);

        query.setParameter("cpf", cpf);
        query.setParameter("email", email);
        query.setParameter("usuario", usuario);
        query.setParameter("tipoPessoa", tipoPessoa);

        return getResultadoUnico(query);
    }

    @Override
    public Set<PessoaEntity> listaParceiros() {
        return new HashSet<PessoaEntity>(entityManager.createNamedQuery("listaParceiros", PessoaEntity.class).getResultList());
    }

}
