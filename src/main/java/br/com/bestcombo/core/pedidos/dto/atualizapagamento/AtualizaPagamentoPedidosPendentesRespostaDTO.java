package br.com.bestcombo.core.pedidos.dto.atualizapagamento;

import lombok.Getter;
import lombok.Setter;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;

@Getter
@Setter
public class AtualizaPagamentoPedidosPendentesRespostaDTO extends RespostaDTO {

    private Integer quantidadeRegistrosAtualizados;

}
