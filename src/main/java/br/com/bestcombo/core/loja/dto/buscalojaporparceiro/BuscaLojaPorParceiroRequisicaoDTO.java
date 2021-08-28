package br.com.bestcombo.core.loja.dto.buscalojaporparceiro;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.ws.rs.PathParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.BUSCA_LOJA_POR_PARCEIRO)
public class BuscaLojaPorParceiroRequisicaoDTO extends RequisicaoDTO<BuscaLojaPorParceiroRespostaDTO> {

    @PathParam("codigoParceiro")
    @NotNull(message = "O código do parceiro é obrigatório")
    private UUID codigoParceiro;
}
