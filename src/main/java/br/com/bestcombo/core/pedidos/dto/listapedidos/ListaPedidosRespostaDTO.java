package br.com.bestcombo.core.pedidos.dto.listapedidos;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.pedidos.dto.PedidoDTO;

@Getter
@Setter
public class ListaPedidosRespostaDTO extends RespostaDTO {

    private Collection<PedidoDTO> pedidos;

}
