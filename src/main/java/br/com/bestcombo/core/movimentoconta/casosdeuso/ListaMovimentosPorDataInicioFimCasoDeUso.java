package br.com.bestcombo.core.movimentoconta.casosdeuso;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.movimentoconta.dto.MovimentoContaDTO;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimRequisicaoDTO;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimRespostaDTO;
import br.com.bestcombo.core.movimentoconta.entity.MovimentoContaEntity;
import br.com.bestcombo.core.movimentoconta.mapper.MovimentoContaMapper;
import br.com.bestcombo.ports.dao.MovimentoContaDAO;
import br.com.bestcombo.ports.service.SegurancaService;

@RequiredArgsConstructor
@ApplicationScoped
@CasoDeUso(CasosDeUso.LISTA_MOVIMENTOS_DATA_INICIO_FIM)
public class ListaMovimentosPorDataInicioFimCasoDeUso extends AbstractCasoDeUso<ListaMovimentosPorDataInicioFimRequisicaoDTO, ListaMovimentosPorDataInicioFimRespostaDTO> {

    final MovimentoContaDAO movimentoContaDAO;

    final SegurancaService segurancaService;

    @Override
    protected void processa(ListaMovimentosPorDataInicioFimRequisicaoDTO req, ListaMovimentosPorDataInicioFimRespostaDTO res) throws NegocioException {
        Collection<MovimentoContaEntity> movimentos = movimentoContaDAO.listaMovimentosPorDataIncioFimECodigoParceiro(req.getDataInicio(), req.getDataFim(), segurancaService.getCodigoUsuarioLogado());

        List<MovimentoContaDTO> dtos = movimentos.stream()
                .map(MovimentoContaMapper::mapperParaDTO)
                .collect(Collectors.toList());

        res.setMovimentos(dtos);
    }

}
