package br.com.bestcombo.core.casosdeuso.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RequisicaoDTO<RESPOSTA extends RespostaDTO> {

    @JsonIgnore
    private UUID id = UUID.randomUUID();

    @JsonIgnore
    private RESPOSTA resposta;

}
