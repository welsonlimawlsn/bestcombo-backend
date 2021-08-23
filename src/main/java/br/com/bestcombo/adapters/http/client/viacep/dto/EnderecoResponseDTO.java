package br.com.bestcombo.adapters.http.client.viacep.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EnderecoResponseDTO {

    @NotEmpty
    private String cep;

    @NotEmpty
    private String logradouro;

    @NotEmpty
    private String bairro;

    @NotEmpty
    private String localidade;

    @NotEmpty
    private String uf;

}
