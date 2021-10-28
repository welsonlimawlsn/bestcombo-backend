package br.com.bestcombo.core.produtos.dto.listaprodutos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.produtos.dto.ProdutoDTO;

@Getter
@Setter
public class ListaProdutosRespostaDTO extends RespostaDTO {

    private List<ProdutoDTO> produtos;

}
