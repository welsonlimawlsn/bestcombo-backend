package br.com.bestcombo.core.saldo.casosdeuso;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosRequisicaoDTO;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosRespostaDTO;
import br.com.bestcombo.core.saldo.entity.SaldoEntity;
import br.com.bestcombo.ports.dao.LojaDAO;
import br.com.bestcombo.ports.service.SaldoService;
import br.com.bestcombo.ports.service.SegurancaService;

@RequiredArgsConstructor
@ApplicationScoped
@CasoDeUso(CasosDeUso.CONSULTA_SALDOS)
public class ConsultaSaldosCasoDeUso extends AbstractCasoDeUso<ConsultaSaldosRequisicaoDTO, ConsultaSaldosRespostaDTO> {

    final SaldoService saldoService;

    final LojaDAO lojaDAO;

    final SegurancaService segurancaService;

    @Override
    protected void processa(ConsultaSaldosRequisicaoDTO req, ConsultaSaldosRespostaDTO res) throws NegocioException {
        LojaEntity lojaEntity = lojaDAO.buscaLojaPorParceiro(this.segurancaService.getCodigoUsuarioLogado()).orElseThrow();

        SaldoEntity saldoDia = saldoService.getSaldoDia(lojaEntity.getCodigo());

        res.setValorDisponivel(saldoDia.getValorDisponivel());
        res.setValorAReceber(saldoDia.getValorFuturo());
    }

}
