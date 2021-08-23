package br.com.bestcombo.ports.dao;

import java.util.Optional;

public interface DAO<ENTIDADE, CHAVE_PRIMARIA> {

    void persiste(ENTIDADE entidade);

    Optional<ENTIDADE> buscaPorId(CHAVE_PRIMARIA id);

}
