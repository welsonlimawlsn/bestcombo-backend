package br.com.bestcombo.ports.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DAO<ENTIDADE, CHAVE_PRIMARIA> {

    void persiste(ENTIDADE entidade);

    Optional<ENTIDADE> buscaPorId(CHAVE_PRIMARIA id);

    Collection<ENTIDADE> lista(Map<String, Object> queryParameters);

    Collection<ENTIDADE> lista();

}
