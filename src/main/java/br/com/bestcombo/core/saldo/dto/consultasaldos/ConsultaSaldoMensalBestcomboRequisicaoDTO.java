package br.com.bestcombo.core.saldo.dto.consultasaldos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@CasoDeUso(CasosDeUso.CONSULTA_SALDOS_POR_MES)
@Getter
@Setter
public class ConsultaSaldoMensalBestcomboRequisicaoDTO extends RequisicaoDTO<ConsultaSaldoMensalBestcomboRespostaDTO> {

    @QueryParam("mes")
    @NotNull(message = "O mes é obrigatório")
    @Min(value = 1)
    @Max(value = 12)
    private Integer mes;

    @NotNull(message = "O ano é obrigatório")
    @QueryParam("ano")
    private Integer ano;

}
