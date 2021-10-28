package br.com.bestcombo.adapters.dao;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.categoria.entity.CategoriaEntity;
import br.com.bestcombo.ports.dao.CategoriaDAO;

@ApplicationScoped
public class CategoriaDAOImpl extends DAOImpl<CategoriaEntity, Integer> implements CategoriaDAO {

    public CategoriaDAOImpl() {
        super(CategoriaEntity.class);
    }

}
