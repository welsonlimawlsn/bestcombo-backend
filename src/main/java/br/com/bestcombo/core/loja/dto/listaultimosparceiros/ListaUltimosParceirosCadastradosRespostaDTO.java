package br.com.bestcombo.core.loja.dto.listaultimosparceiros;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.loja.dto.LojaDTO;

@Getter
@Setter
public class ListaUltimosParceirosCadastradosRespostaDTO extends RespostaDTO {

    private List<LojaDTO> lojas;

}
