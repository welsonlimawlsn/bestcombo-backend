package br.com.bestcombo.adapters.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.ports.dao.ProdutoDAO;

@ApplicationScoped
public class ProdutoDAOImpl extends DAOImpl<ProdutoEntity, UUID> implements ProdutoDAO {

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
    public Optional<ProdutoEntity> buscaPorIdEParceiro(UUID codigo, UUID codigoUsuarioLogado) {
        TypedQuery<ProdutoEntity> query = entityManager.createQuery("SELECT p FROM ProdutoEntity p WHERE p.codigo = :codigoProduto AND p.loja.parceiro.codigo = :codigoParceiro", ProdutoEntity.class);

        query.setParameter("codigoParceiro", codigoUsuarioLogado);
        query.setParameter("codigoProduto", codigo);

        return getResultadoUnico(query);
    }

    @Override
    public Collection<ProdutoEntity> buscaProdutoPorTermo(String termo) {
        TypedQuery<ProdutoEntity> query = entityManager.createQuery("SELECT P FROM ProdutoEntity P" +
                " JOIN FETCH P.categorias C" +
                " WHERE P.ativo = TRUE" +
                " AND (LOWER(P.nome) LIKE :TERMO" +
                " OR LOWER(P.descricao) LIKE :TERMO" +
                " OR LOWER(C.nome) LIKE :TERMO)", ProdutoEntity.class);

        query.setParameter("TERMO", "%" + termo.replace(" ", "%") + "%");

        return new HashSet<>(query.getResultList());
    }

    @Override
    public Collection<ProdutoEntity> listaUltimosProdutosCadastrados() {
        TypedQuery<ProdutoEntity> query = entityManager.createQuery("SELECT p FROM ProdutoEntity p join FETCH p.categorias c WHERE p.ativo = true order by p.dataCadastro desc ", ProdutoEntity.class);
        query.setMaxResults(8);

        return new HashSet<>(query.getResultList());
    }

    @Override
    protected String getSqlListar() {
        return "SELECT e FROM ProdutoEntity e JOIN FETCH e.categorias c WHERE e.ativo = true";
    }

}
