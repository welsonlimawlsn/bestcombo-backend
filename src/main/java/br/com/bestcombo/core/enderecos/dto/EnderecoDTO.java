package br.com.bestcombo.core.enderecos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import br.com.bestcombo.core.validacao.CEP;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    @NotEmpty(message = "O CEP do endereço é obrigatório.")
    @CEP(message = "O CEP informado é inválido.")
    private String cep;

    @NotEmpty(message = "O número do endereço é obrigatório.")
    private String numero;

    private String rua;

    private String estado;

    private String cidade;

    private String bairro;

    private String complemento;

}
