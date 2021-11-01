package br.com.bestcombo.core.movimentoconta.casosdeuso;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimBestcomboRequisicaoDTO;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimBestcomboRespostaDTO;
import br.com.bestcombo.ports.service.AdminService;

@ApplicationScoped
@CasoDeUso(CasosDeUso.LISTA_MOVIMENTOS_DATA_INICIO_FIM_BESTCOMBO)
public class ListaMovimentosPorDataInicioFimBestcomboCasoDeUso extends ListaMovimentosPorDataInicioFimCasoDeUso<ListaMovimentosPorDataInicioFimBestcomboRequisicaoDTO, ListaMovimentosPorDataInicioFimBestcomboRespostaDTO> {

    @Inject
    AdminService adminService;

    @Override
    protected UUID getCodigoPessoa() {
        return adminService.getCodigoBestcombo();
    }

}
