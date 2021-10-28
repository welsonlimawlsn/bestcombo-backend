package br.com.bestcombo.core.loja.dto.buscalojaportermo;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.loja.dto.LojaDTO;

@Getter
@Setter
public class BuscaLojaPorTermoRespostaDTO extends RespostaDTO {

    private Collection<LojaDTO> lojas;

}
