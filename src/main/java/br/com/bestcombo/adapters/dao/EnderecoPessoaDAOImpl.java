package br.com.bestcombo.adapters.dao;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.enderecos.entity.EnderecoPessoaEntity;
import br.com.bestcombo.ports.dao.EnderecoPessoaDAO;

@ApplicationScoped
public class EnderecoPessoaDAOImpl extends DAOImpl<EnderecoPessoaEntity, UUID> implements EnderecoPessoaDAO {

    public EnderecoPessoaDAOImpl() {
        super(EnderecoPessoaEntity.class);
    }

}
