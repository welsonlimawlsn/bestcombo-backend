package br.com.bestcombo.core.pessoas.dto.novapessoa;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;

@Getter
@Setter
public class NovaPessoaRequisicaoDTO<RESPOSTA extends NovaPessoaRespostaDTO> extends RequisicaoDTO<RESPOSTA> {

    @NotEmpty(message = "O nome é obrigatório.")
    private String nome;

    @NotEmpty(message = "O sobrenome é obrigatório.")
    private String sobrenome;

    @NotEmpty(message = "O e-mail é obrigatório.")
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

}
