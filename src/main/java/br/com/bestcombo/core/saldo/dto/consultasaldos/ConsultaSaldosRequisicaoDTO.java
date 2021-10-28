package br.com.bestcombo.core.saldo.dto.consultasaldos;

import lombok.Getter;
import lombok.Setter;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.CONSULTA_SALDOS)
public class ConsultaSaldosRequisicaoDTO extends RequisicaoDTO<ConsultaSaldosRespostaDTO> {

}
