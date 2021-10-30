package br.com.bestcombo.core.saldo.casosdeuso;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosParceiroRequisicaoDTO;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosParceiroRespostaDTO;
import br.com.bestcombo.ports.dao.LojaDAO;
import br.com.bestcombo.ports.service.SegurancaService;

@ApplicationScoped
@CasoDeUso(CasosDeUso.CONSULTA_SALDOS_PARCEIROS)
public class ConsultaSaldosParceiroCasoDeUso extends ConsultaSaldosCasoDeUso<ConsultaSaldosParceiroRequisicaoDTO, ConsultaSaldosParceiroRespostaDTO> {

    @Inject
    LojaDAO lojaDAO;

    @Inject
    SegurancaService segurancaService;

    @Override
    protected LojaEntity getLoja() {
        return lojaDAO.buscaLojaPorParceiro(this.segurancaService.getCodigoUsuarioLogado()).orElseThrow();
    }

}
