package br.com.bestcombo.core.casosdeuso.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisicaoDTO<RESPOSTA extends RespostaDTO> {

    @JsonIgnore
    private RESPOSTA resposta;

}
