package br.com.bestcombo.ports.dao;

import java.util.List;

import br.com.bestcombo.core.casosdeuso.CasoDeUsoEntity;
import br.com.bestcombo.core.papel.entity.PapelEntity;

public interface PapelDAO extends DAO<PapelEntity, Integer> {

    List<String> buscaPapeisPorCasoDeUso(CasoDeUsoEntity casoDeUso);

    List<String> buscaPapeisPorCodigoCasoDeUso(Integer casoDeUso);

}
