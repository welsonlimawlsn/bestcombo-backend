package br.com.bestcombo.core.saldo.dto.consultasaldos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;

@Getter
@Setter
public class ConsultaSaldoMensalBestcomboRespostaDTO extends RespostaDTO {

    private BigDecimal saldoBruto;

    private BigDecimal saldoLiquidoSemImpostos;

    private BigDecimal saldoLiquidoComImpostos;

    private BigDecimal imposto;

}
