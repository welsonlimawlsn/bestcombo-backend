package br.com.bestcombo.core.solicitacaosaque.casosdeuso;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.solicitacaosaque.dto.listasolicitacoes.ListaSolicitacoesSaquePendentesRequisicaoDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.listasolicitacoes.ListaSolicitacoesSaquePendentesRespostaDTO;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;
import br.com.bestcombo.core.solicitacaosaque.mapper.SolicitacaoSaqueMapper;
import br.com.bestcombo.ports.dao.SolicitacaoSaqueDAO;

@RequiredArgsConstructor
@ApplicationScoped
@CasoDeUso(CasosDeUso.LISTA_SOLICITACOES_SAQUE_PENDENTES)
public class ListaSolicitacoesSaquePendentesCasoDeUso extends AbstractCasoDeUso<ListaSolicitacoesSaquePendentesRequisicaoDTO, ListaSolicitacoesSaquePendentesRespostaDTO> {

    final SolicitacaoSaqueDAO solicitacaoSaqueDAO;

    @Override
    protected void processa(ListaSolicitacoesSaquePendentesRequisicaoDTO req, ListaSolicitacoesSaquePendentesRespostaDTO res) throws NegocioException {

        Collection<SolicitacaoSaqueEntity> entities = solicitacaoSaqueDAO.listaPendentes();

        res.setSolicitacoesSaque(entities.stream()
                .map(SolicitacaoSaqueMapper::mapperParaDTO)
                .collect(Collectors.toList()));
    }

}
