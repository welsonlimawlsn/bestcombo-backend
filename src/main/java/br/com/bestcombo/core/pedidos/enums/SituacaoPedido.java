package br.com.bestcombo.core.pedidos.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum SituacaoPedido {

    PEDIDO_AGUARDANDO_APROVACAO_PARCEIRO(1, "Aguardando Aprovação do Parceiro"),
    PEDIDO_AGUARDANDO_PAGAMENTO_CLIENTE(2, "Aguardando Pagamento do Cliente"),
    PARCEIRO_PREPARANDO_PEDIDO(3, "Parceiro Preparando Pedido"),
    PEDIDO_ROTA_ENTREGA(4, "Parceiro Preparando Pedido"),
    PEDIDO_CONCLUIDO(5, "Pedido concluido"),
    PEDIDO_CANCELADO(6, "Pedido Cancelado");

    private final Integer codigo;

    private final String descricao;

    public static SituacaoPedido getPorCodigo(Integer codigo) {
        return Arrays.stream(values())
                .filter(situacaoPedido -> situacaoPedido.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow();
    }
}
