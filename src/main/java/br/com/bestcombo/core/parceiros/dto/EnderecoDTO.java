package br.com.bestcombo.core.parceiros.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import br.com.bestcombo.core.validacao.CEP;

@Getter
@Setter
public class EnderecoDTO {

    @NotEmpty(message = "O CEP do endereço é obrigatório.")
    @CEP(message = "O CEP informado é inválido.")
    private String cep;

    @NotEmpty(message = "O número do endereço é obrigatório.")
    private String numero;

    private String complemento;

    private String referencia;

}
