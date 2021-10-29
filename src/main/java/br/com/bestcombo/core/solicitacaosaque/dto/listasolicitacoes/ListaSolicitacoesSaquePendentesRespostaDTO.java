package br.com.bestcombo.core.solicitacaosaque.dto.listasolicitacoes;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.SolicitacaoSaqueDTO;

@Getter
@Setter
public class ListaSolicitacoesSaquePendentesRespostaDTO extends RespostaDTO {

    private Collection<SolicitacaoSaqueDTO> solicitacoesSaque;

}
