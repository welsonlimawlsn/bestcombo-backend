package br.com.bestcombo.core.produtos.dto.excluiproduto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.ws.rs.PathParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.EXCLUI_PRODUTO)
public class ExcluiProdutoRequisicaoDTO extends RequisicaoDTO<ExcluiProdutoRespostaDTO> {

    @PathParam("codigo")
    private UUID codigo;

}
