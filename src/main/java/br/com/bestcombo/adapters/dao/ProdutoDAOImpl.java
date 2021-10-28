package br.com.bestcombo.adapters.dao;

import java.util.Collection;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.ports.dao.ProdutoDao;

@ApplicationScoped
public class ProdutoDAOImpl extends DAOImpl<ProdutoEntity, UUID> implements ProdutoDao {

    public ProdutoDAOImpl() {
        super(ProdutoEntity.class);
    }

    @Override
    public Collection<ProdutoEntity> buscaPorIdsECodigoLoja(Collection<UUID> codigoProdutos, LojaEntity loja) {
        TypedQuery<ProdutoEntity> query = entityManager.createQuery("SELECT p FROM ProdutoEntity p WHERE p.loja = :loja AND p.codigo in (:codigos)", ProdutoEntity.class);

        query.setParameter("codigos", codigoProdutos);
        query.setParameter("loja", loja);

        return query.getResultList();
    }

    @Override
    protected String getSqlListar() {
        return "SELECT e FROM ProdutoEntity e JOIN FETCH e.categorias c WHERE 1 = 1";
    }

}
