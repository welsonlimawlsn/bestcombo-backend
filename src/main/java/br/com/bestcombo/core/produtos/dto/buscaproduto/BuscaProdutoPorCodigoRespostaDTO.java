package br.com.bestcombo.core.produtos.dto.buscaproduto;

import lombok.Getter;
import lombok.Setter;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.produtos.dto.ProdutoDTO;

@Getter
@Setter
public class BuscaProdutoPorCodigoRespostaDTO extends RespostaDTO {

    private ProdutoDTO produto;

}
