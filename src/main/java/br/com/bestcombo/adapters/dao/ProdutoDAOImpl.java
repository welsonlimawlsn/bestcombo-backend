package br.com.bestcombo.adapters.dao;

import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.ports.dao.ProdutoDao;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class ProdutoDAOImpl extends DAOImpl<ProdutoEntity,UUID> implements ProdutoDao {

    public ProdutoDAOImpl() {
        super(ProdutoEntity.class);
    }
}
