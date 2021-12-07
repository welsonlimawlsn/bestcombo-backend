package br.com.bestcombo.core.saldo.casosdeuso;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Month;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldoMensalBestcomboRequisicaoDTO;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldoMensalBestcomboRespostaDTO;
import br.com.bestcombo.ports.service.AdminService;
import br.com.bestcombo.ports.service.SaldoService;

@ApplicationScoped
@CasoDeUso(CasosDeUso.CONSULTA_SALDOS_POR_MES)
@RequiredArgsConstructor
public class ConsultaSaldoMensalBestComboCasoDeUso extends AbstractCasoDeUso<ConsultaSaldoMensalBestcomboRequisicaoDTO, ConsultaSaldoMensalBestcomboRespostaDTO> {

    private final AdminService adminService;

    private final SaldoService saldoService;

    @Override
    protected void processa(ConsultaSaldoMensalBestcomboRequisicaoDTO req, ConsultaSaldoMensalBestcomboRespostaDTO res) throws NegocioException {
        Map<String, BigDecimal> saldoMes = saldoService.getCreditosDebitosPorMes(Month.of(req.getMes()), req.getAno(), adminService.getLojaAdministrador().getCodigo());
        BigDecimal saldoBruto = saldoMes.get("CREDITO");
        BigDecimal custoTransacoes = saldoMes.get("DEBITO");
        BigDecimal valorISS = saldoBruto.multiply(new BigDecimal("0.02"), MathContext.DECIMAL128);
        BigDecimal saldoLiquidoSemImpostos = saldoBruto.subtract(custoTransacoes, MathContext.DECIMAL128);

        res.setSaldoBruto(saldoBruto.setScale(2, RoundingMode.HALF_EVEN));
        res.setSaldoLiquidoSemImpostos(saldoLiquidoSemImpostos.setScale(2, RoundingMode.HALF_EVEN));
        res.setSaldoLiquidoComImpostos(saldoLiquidoSemImpostos.subtract(valorISS).setScale(2, RoundingMode.HALF_EVEN));
        res.setImposto(valorISS.setScale(2, RoundingMode.HALF_EVEN));
    }

}
