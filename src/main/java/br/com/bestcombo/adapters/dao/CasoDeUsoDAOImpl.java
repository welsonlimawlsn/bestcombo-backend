package br.com.bestcombo.adapters.dao;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.CasoDeUsoEntity;
import br.com.bestcombo.ports.dao.CasoDeUsoDAO;

@ApplicationScoped
public class CasoDeUsoDAOImpl extends DAOImpl<CasoDeUsoEntity, Integer> implements CasoDeUsoDAO {

    public CasoDeUsoDAOImpl() {
        super(CasoDeUsoEntity.class);
    }

}
