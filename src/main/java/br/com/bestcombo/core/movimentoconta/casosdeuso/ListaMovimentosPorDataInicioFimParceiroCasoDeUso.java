package br.com.bestcombo.core.movimentoconta.casosdeuso;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimParceiroRequisicaoDTO;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimParceiroRespostaDTO;
import br.com.bestcombo.ports.service.SegurancaService;

@ApplicationScoped
@CasoDeUso(CasosDeUso.LISTA_MOVIMENTOS_DATA_INICIO_FIM_PARCEIRO)
public class ListaMovimentosPorDataInicioFimParceiroCasoDeUso extends ListaMovimentosPorDataInicioFimCasoDeUso<ListaMovimentosPorDataInicioFimParceiroRequisicaoDTO, ListaMovimentosPorDataInicioFimParceiroRespostaDTO> {

    @Inject
    SegurancaService segurancaService;

    @Override
    protected UUID getCodigoPessoa() {
        return segurancaService.getCodigoUsuarioLogado();
    }

}
