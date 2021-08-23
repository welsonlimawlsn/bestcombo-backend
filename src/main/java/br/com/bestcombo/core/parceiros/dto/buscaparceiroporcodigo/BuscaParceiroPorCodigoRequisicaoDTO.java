package br.com.bestcombo.core.parceiros.dto.buscaparceiroporcodigo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.ws.rs.PathParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@CasoDeUso(CasosDeUso.BUSCA_PARCEIRO_POR_CODIGO)
@Getter
@Setter
public class BuscaParceiroPorCodigoRequisicaoDTO extends RequisicaoDTO<BuscaParceiroPorCodigoRespostaDTO> {

    @PathParam("codigo")
    private UUID codigo;

}
