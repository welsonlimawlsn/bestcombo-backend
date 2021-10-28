package br.com.bestcombo.core.loja.dto.cadastroloja;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;

@CasoDeUso(CasosDeUso.CADASTRA_LOJA)
@Getter
@Setter
public class CadastroLojaRequisicaoDTO extends RequisicaoDTO<CadastroLojaRespostaDTO> {

    @NotEmpty(message = "O CNPJ é obrigatório")
    @CNPJ(message = "O CNPJ é inválido")
    private String cnpj;

    @NotEmpty(message = "O nome da loja é obrigatório!")
    private String nome;

    @NotEmpty(message = "A descrição da loja é obrigatória")
    private String descricao;

    @NotEmpty(message = "A imagem da loja é obrigatória")
    private String imagem;

    @Valid
    @NotNull(message = "O endereço é obrigatório")
    private EnderecoDTO endereco;

    @NotNull(message = "O código do parceiro é obrigatório")
    private UUID codigoParceiro;

    @NotNull(message = "A loja deve oferecer no mínimo uma categoria")
    @Size(min = 1, message = "A loja deve oferecer no mínimo uma categoria")
    private List<Integer> categorias;

}
