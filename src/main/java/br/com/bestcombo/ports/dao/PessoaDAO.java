package br.com.bestcombo.ports.dao;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.bestcombo.core.pessoas.entity.PessoaEntity;

public interface PessoaDAO extends DAO<PessoaEntity, UUID> {

    Optional<PessoaEntity> buscaPorCpfOuEmailOuUsuario(String cpf, String email, String usuario, Integer tipoPessoa);

    Set<PessoaEntity> listaParceiros();

}
