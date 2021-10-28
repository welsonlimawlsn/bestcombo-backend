package br.com.bestcombo.ports.service;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import br.com.bestcombo.core.pedidos.dto.novopedido.NovoPedidoRequisicaoDTO;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedido;

public interface PagamentoService {

    void processaPagamento(PedidoEntity pedido);

    void preparaPagamento(PedidoEntity pedido, NovoPedidoRequisicaoDTO requisicao);

    Map<UUID, SituacaoPedido> getSituacaoPedidos(Collection<PedidoEntity> pedidosPendentes);

}
