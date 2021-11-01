package br.com.bestcombo.core.movimentoconta.dto.listamovimentos;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;

@Getter
@Setter
public class ListaMovimentosPorDataInicioFimRequisicaoDTO<RES extends ListaMovimentosPorDataInicioFimRespostaDTO> extends RequisicaoDTO<RES> {

    @QueryParam("dataInicio")
    @NotNull(message = "Data de inicio é obrigatório")
    private ZonedDateTime dataInicio;

    @QueryParam("dataFim")
    private ZonedDateTime dataFim;

}
