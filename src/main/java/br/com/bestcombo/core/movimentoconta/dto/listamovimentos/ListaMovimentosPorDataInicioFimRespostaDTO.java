package br.com.bestcombo.core.movimentoconta.dto.listamovimentos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.movimentoconta.dto.MovimentoContaDTO;

@Getter
@Setter
public class ListaMovimentosPorDataInicioFimRespostaDTO extends RespostaDTO {

    private List<MovimentoContaDTO> movimentos;

}
