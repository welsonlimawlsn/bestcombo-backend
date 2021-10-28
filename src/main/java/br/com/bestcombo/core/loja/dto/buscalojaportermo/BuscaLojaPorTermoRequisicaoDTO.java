package br.com.bestcombo.core.loja.dto.buscalojaportermo;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.QueryParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.BUSCA_LOJA_POR_TERMO)
public class BuscaLojaPorTermoRequisicaoDTO extends RequisicaoDTO<BuscaLojaPorTermoRespostaDTO> {

    @QueryParam("termo")
    private String termo;

}
