package br.com.bestcombo.core.pedidos.dto.novopedido;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProdutoPedidoDTO {

    @NotNull(message = "Deve informar o código do produto")
    private UUID codigo;

    @NotNull(message = "Deve informar a quantidade do produto")
    private Integer quantidade;

}
