package br.com.bestcombo.adapters.dao;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedido;
import br.com.bestcombo.ports.dao.PedidoDAO;

@ApplicationScoped
public class PedidoDAOImpl extends DAOImpl<PedidoEntity, UUID> implements PedidoDAO {

    public PedidoDAOImpl() {
        super(PedidoEntity.class);
    }

    @Override
    public Optional<PedidoEntity> buscaPorIdECodigoParceiro(UUID codigoPedido, UUID codigoUsuarioLogado) {

        TypedQuery<PedidoEntity> query = entityManager.createQuery("SELECT p FROM PedidoEntity p" +
                " JOIN FETCH p.loja l " +
                "JOIN FETCH p.cliente c" +
                " JOIN FETCH p.produtos pr " +
                "JOIN FETCH c.enderecos e " +
                "JOIN FETCH pr.produto pro" +
                " JOIN FETCH pro.categorias ca" +
                " WHERE p.codigo = :codigoPedido AND l.parceiro.codigo = :codigoParceiro", PedidoEntity.class);

        query.setParameter("codigoParceiro", codigoUsuarioLogado);
        query.setParameter("codigoPedido", codigoPedido);

        return getResultadoUnico(query);
    }

    @Override
    public Collection<PedidoEntity> buscaPendentesPagamento() {
        TypedQuery<PedidoEntity> query = entityManager.createQuery("SELECT p FROM PedidoEntity p WHERE p.situacao = :situacao", PedidoEntity.class);

        query.setParameter("situacao", SituacaoPedido.PEDIDO_AGUARDANDO_PAGAMENTO_CLIENTE);

        return query.getResultList();
    }

    @Override
    public Optional<PedidoEntity> buscaPorIdECodigoCliente(UUID codigo, UUID codigoUsuarioLogado) {
        TypedQuery<PedidoEntity> query = entityManager.createQuery("SELECT p FROM PedidoEntity p" +
                " JOIN FETCH p.loja l" +
                " JOIN FETCH p.cliente c" +
                " JOIN FETCH c.enderecos e" +
                " JOIN FETCH p.produtos pr" +
                " JOIN FETCH pr.produto pro" +
                " JOIN FETCH pro.categorias ca WHERE p.codigo = :codigoPedido AND c.codigo = :codigoCliente", PedidoEntity.class);

        query.setParameter("codigoCliente", codigoUsuarioLogado);
        query.setParameter("codigoPedido", codigo);

        return getResultadoUnico(query);
    }

    @Override
    protected String getSqlListar() {
        return "SELECT e FROM PedidoEntity e join fetch e.loja WHERE 1 = 1";
    }

}
