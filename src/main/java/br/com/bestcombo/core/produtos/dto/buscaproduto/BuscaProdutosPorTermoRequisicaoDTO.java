package br.com.bestcombo.core.produtos.dto.buscaproduto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.QueryParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@CasoDeUso(CasosDeUso.BUSCA_PRODUTO_POR_TERMO)
@Getter
@Setter
public class BuscaProdutosPorTermoRequisicaoDTO extends RequisicaoDTO<BuscaProdutosPorTermoRespostaDTO> {

    @QueryParam("termo")
    @NotEmpty(message = "O termo é obrigatório")
    private String termo;

}
