package br.com.bestcombo.core.produtos.dto.novoproduto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.CADASTRO_PRODUTO)
public class NovoProdutoRequisicaoDTO extends RequisicaoDTO<NovoProdutoRespostaDTO> {

    @NotEmpty(message = "O Nome é obrigatório.")
    private String nome;

    @NotEmpty(message = "A Descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "O Preço é obrigatório.")
    @DecimalMin(value = "0.01")
    @Digits(integer = 12, fraction = 2, message = "O preço deve ter 2 casas após o ponto.")
    private BigDecimal preco;

    @NotNull(message = "O produto deve possuir uma quantidade de pessoas que serve")
    @Min(value = 1, message = "O produto deve servir pelo menos uma pessoa")
    private Integer quantidadePessoas;

    @NotEmpty(message = "O produto deve ter uma imagem")
    private String imagem;

    @NotNull(message = "O Codigo da Loja é obrigatório.")
    private UUID codigoLoja;

    @NotNull(message = "O produto deve estar insirido em pelo menos uma categoria")
    @Size(min = 1, message = "O produto deve estar insirido em pelo menos uma categoria")
    private Set<Integer> categorias;

}
