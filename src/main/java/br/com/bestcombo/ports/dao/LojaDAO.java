package br.com.bestcombo.ports.dao;

import java.util.Optional;
import java.util.UUID;

import br.com.bestcombo.core.loja.entity.LojaEntity;

public interface LojaDAO extends DAO<LojaEntity, UUID> {

    Optional<LojaEntity> buscaLojaPorParceiro(UUID codigoParceiro);
}
