package br.com.bestcombo.core.saldo.dto.consultasaldos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;

@Getter
@Setter
public class ConsultaSaldosRespostaDTO extends RespostaDTO {

    private BigDecimal valorDisponivel;

    private BigDecimal valorAReceber;

}
