package br.com.bestcombo.core.solicitacaosaque.casosdeuso;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.solicitacaosaque.dto.alterasituacao.AlteraSituacaoSolicitacaoSaqueRequisicaoDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.alterasituacao.AlteraSituacaoSolicitacaoSaqueRespostaDTO;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;
import br.com.bestcombo.core.solicitacaosaque.enums.SituacaoSolicitacaoSaque;
import br.com.bestcombo.ports.dao.SolicitacaoSaqueDAO;
import br.com.bestcombo.ports.service.MovimentoService;

@RequiredArgsConstructor
@ApplicationScoped
@CasoDeUso(CasosDeUso.ALTERA_SITUACAO_SOLICITACAO_SAQUE)
public class AlteraSituacaoSolicitacaoSaqueCasoDeUso extends AbstractCasoDeUso<AlteraSituacaoSolicitacaoSaqueRequisicaoDTO, AlteraSituacaoSolicitacaoSaqueRespostaDTO> {

    final SolicitacaoSaqueDAO solicitacaoSaqueDAO;

    final MovimentoService movimentoService;

    @Override
    protected void processa(AlteraSituacaoSolicitacaoSaqueRequisicaoDTO req, AlteraSituacaoSolicitacaoSaqueRespostaDTO res) throws NegocioException {
        SolicitacaoSaqueEntity solicitacaoSaqueEntity = solicitacaoSaqueDAO.buscaPorId(req.getCodigoSolicitacao()).orElseThrow();

        solicitacaoSaqueEntity.setSituacaoSolicitacaoSaque(req.getSituacaoSolicitacaoSaque());

        if (req.getSituacaoSolicitacaoSaque().equals(SituacaoSolicitacaoSaque.CONCLUIDO)) {
            movimentoService.criaMovimentoSaque(solicitacaoSaqueEntity);
        }
    }

}
