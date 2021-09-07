package br.com.bestcombo.core.loja.dto.listalojas;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.loja.dto.LojaDTO;

@Setter
@Getter
public class ListaLojasRespostaDTO extends RespostaDTO {

    private List<LojaDTO> lojas;

}
