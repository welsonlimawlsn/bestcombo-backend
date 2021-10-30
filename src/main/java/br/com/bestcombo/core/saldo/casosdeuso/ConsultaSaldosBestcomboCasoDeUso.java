package br.com.bestcombo.core.saldo.casosdeuso;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosBestcomboRequisicaoDTO;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosBestcomboRespostaDTO;
import br.com.bestcombo.ports.service.AdminService;

@ApplicationScoped
@CasoDeUso(CasosDeUso.CONSULTA_SALDOS_BESTCOMBO)
public class ConsultaSaldosBestcomboCasoDeUso extends ConsultaSaldosCasoDeUso<ConsultaSaldosBestcomboRequisicaoDTO, ConsultaSaldosBestcomboRespostaDTO> {

    AdminService adminService;

    @Override
    protected LojaEntity getLoja() {
        return adminService.getLojaAdministrador();
    }

}
