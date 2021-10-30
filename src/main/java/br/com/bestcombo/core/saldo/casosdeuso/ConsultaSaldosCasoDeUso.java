package br.com.bestcombo.core.saldo.casosdeuso;

import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosRequisicaoDTO;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosRespostaDTO;
import br.com.bestcombo.core.saldo.entity.SaldoEntity;
import br.com.bestcombo.ports.service.SaldoService;

public abstract class ConsultaSaldosCasoDeUso<REQ extends ConsultaSaldosRequisicaoDTO<RES>, RES extends ConsultaSaldosRespostaDTO> extends AbstractCasoDeUso<REQ, RES> {

    @Inject
    SaldoService saldoService;

    @Override
    protected void processa(REQ req, RES res) throws NegocioException {
        LojaEntity lojaEntity = getLoja();

        SaldoEntity saldoDia = saldoService.getSaldoDia(lojaEntity.getCodigo());

        res.setValorDisponivel(saldoDia.getValorDisponivel());
        res.setValorAReceber(saldoDia.getValorFuturo());
    }

    protected abstract LojaEntity getLoja();

}