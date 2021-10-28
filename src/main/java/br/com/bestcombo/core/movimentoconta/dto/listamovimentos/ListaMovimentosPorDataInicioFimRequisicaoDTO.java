package br.com.bestcombo.core.movimentoconta.dto.listamovimentos;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.LISTA_MOVIMENTOS_DATA_INICIO_FIM)
public class ListaMovimentosPorDataInicioFimRequisicaoDTO extends RequisicaoDTO<ListaMovimentosPorDataInicioFimRespostaDTO> {

    @QueryParam("dataInicio")
    @NotNull(message = "Data de inicio é obrigatório")
    private ZonedDateTime dataInicio;

    @QueryParam("dataFim")
    private ZonedDateTime dataFim;

}
