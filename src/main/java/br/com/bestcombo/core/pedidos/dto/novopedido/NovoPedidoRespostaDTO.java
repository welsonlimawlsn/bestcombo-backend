package br.com.bestcombo.core.pedidos.dto.novopedido;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;

@Getter
@Setter
public class NovoPedidoRespostaDTO extends RespostaDTO {

    private UUID codigoPedido;

}
