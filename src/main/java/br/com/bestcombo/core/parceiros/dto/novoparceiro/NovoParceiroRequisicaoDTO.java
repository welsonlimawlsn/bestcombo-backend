package br.com.bestcombo.core.parceiros.dto.novoparceiro;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.parceiros.dto.EnderecoDTO;

@CasoDeUso(CasosDeUso.NOVO_PARCEIRO)
@Getter
@Setter
@ToString(exclude = {"senha"})
public class NovoParceiroRequisicaoDTO extends RequisicaoDTO<NovoParceiroRespostaDTO> {

    @NotEmpty(message = "O CPF/CNPJ do parceiro é obrigatório.")
    private String cpfCnpj;

    @NotEmpty(message = "O nome do parceiro é obrigatório.")
    private String nome;

    @NotNull(message = "O endereço do parceiro é obrigatório.")
    @Valid
    private EnderecoDTO endereco;

    @NotEmpty(message = "O e-mail do parceiro é obrigatório.")
    private String email;

    @NotEmpty(message = "A senha do parceiro é obrigatória.")
    private String senha;

}
