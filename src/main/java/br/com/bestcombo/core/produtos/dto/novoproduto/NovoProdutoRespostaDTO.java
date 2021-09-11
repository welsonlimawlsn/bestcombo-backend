package br.com.bestcombo.core.produtos.dto.novoproduto;


import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class NovoProdutoRespostaDTO extends RespostaDTO {
    private UUID codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;


}
