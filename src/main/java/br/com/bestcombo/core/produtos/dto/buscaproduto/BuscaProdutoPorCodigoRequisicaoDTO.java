package br.com.bestcombo.core.produtos.dto.buscaproduto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.ws.rs.PathParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.BUSCA_PRODUTO_POR_CODIGO)
public class BuscaProdutoPorCodigoRequisicaoDTO extends RequisicaoDTO<BuscaProdutoPorCodigoRespostaDTO> {

    @PathParam("codigo")
    private UUID codigo;

}
