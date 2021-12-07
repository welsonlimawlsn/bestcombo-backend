package br.com.bestcombo.core.pessoas.dto.novapessoa;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;

@Getter
@Setter
public class NovaPessoaRequisicaoDTO<RESPOSTA extends NovaPessoaRespostaDTO> extends RequisicaoDTO<RESPOSTA> {

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @NotEmpty(message = "O nome é obrigatório.")
    private String nome;

    @NotEmpty(message = "O sobrenome é obrigatório.")
    private String sobrenome;

    @NotEmpty(message = "O e-mail é obrigatório.")
    @Pattern(regexp = NovaPessoaRequisicaoDTO.EMAIL_PATTERN, message = "E-mail inválido.")
    private String email;

    @NotEmpty(message = "A senha é obrigatória.")
    private String senha;

    @NotEmpty(message = "O CPF é obrigatório.")
    @CPF(message = "O CPF é inválido.")
    private String cpf;

    @NotEmpty(message = "O nome de usuário é obrigatório.")
    private String usuario;

    @NotNull(message = "O endereço é obrigatório.")
    private EnderecoDTO endereco;

    @Size(min = 10, max = 11, message = "Telefone é inválido")
    @NotEmpty(message = "O telefone é obrigatório")
    private String telefone;

}
