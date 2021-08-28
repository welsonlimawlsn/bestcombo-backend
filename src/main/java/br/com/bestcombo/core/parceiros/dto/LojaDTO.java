package br.com.bestcombo.core.parceiros.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;

@Getter
@Setter
public class LojaDTO {

    @NotEmpty(message = "O nome é obrigatório.")
    private String nome;

    @NotEmpty(message = "O CNPJ é obrigatório.")
    @CNPJ(message = "O CNPJ é inválido.")
    private String cnpj;

    @NotNull(message = "O endereço é obrigatório.")
    private EnderecoDTO endereco;

    @NotNull(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail é inválido")
    private String email;

}
