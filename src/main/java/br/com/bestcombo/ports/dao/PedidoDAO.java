package br.com.bestcombo.ports.dao;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import br.com.bestcombo.core.pedidos.entity.PedidoEntity;

public interface PedidoDAO extends DAO<PedidoEntity, UUID> {

    Optional<PedidoEntity> buscaPorIdECodigoParceiro(UUID codigoPedido, UUID codigoUsuarioLogado);

    Collection<PedidoEntity> buscaPendentesPagamento();

    Optional<PedidoEntity> buscaPorIdECodigoCliente(UUID codigo, UUID codigoUsuarioLogado);

}
