package br.com.bestcombo.ports.dao;

import java.util.Optional;
import java.util.UUID;

import br.com.bestcombo.core.parceiros.entidade.ParceiroEntity;

public interface ParceiroDAO extends DAO<ParceiroEntity, UUID> {

    Optional<ParceiroEntity> buscaPorCpfCnpjOuEmail(String cpfCnpj, String email);

}
