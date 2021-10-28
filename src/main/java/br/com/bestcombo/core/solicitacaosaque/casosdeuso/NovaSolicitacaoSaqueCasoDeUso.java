package br.com.bestcombo.core.solicitacaosaque.casosdeuso;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.solicitacaosaque.dto.novasolicitacao.NovaSolicitacaoSaqueRequisicaoDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.novasolicitacao.NovaSolicitacaoSaqueRespostaDTO;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;
import br.com.bestcombo.ports.dao.LojaDAO;
import br.com.bestcombo.ports.dao.SolicitacaoSaqueDAO;
import br.com.bestcombo.ports.service.SegurancaService;

@RequiredArgsConstructor
@ApplicationScoped
@CasoDeUso(CasosDeUso.NOVA_SOLICITACAO_SAQUE)
public class NovaSolicitacaoSaqueCasoDeUso extends AbstractCasoDeUso<NovaSolicitacaoSaqueRequisicaoDTO, NovaSolicitacaoSaqueRespostaDTO> {

    final SolicitacaoSaqueDAO solicitacaoSaqueDAO;

    final SegurancaService segurancaService;

    final LojaDAO lojaDAO;

    @Override
    protected void processa(NovaSolicitacaoSaqueRequisicaoDTO req, NovaSolicitacaoSaqueRespostaDTO res) throws NegocioException {
        LojaEntity lojaEntity = lojaDAO.buscaLojaPorParceiro(segurancaService.getCodigoUsuarioLogado()).orElseThrow();

        if (solicitacaoSaqueDAO.buscaSolicitacaoPendentePorLoja(lojaEntity).isPresent()) {
            throw new NegocioException(Erro.JA_POSSUI_UMA_SOLICITACAO_EM_ANDAMENTO);
        }

        SolicitacaoSaqueEntity solicitacaoSaque = SolicitacaoSaqueEntity.builder()
                .chavePix(lojaEntity.getChavePix())
                .loja(lojaEntity)
                .valor(req.getValor())
                .build();

        solicitacaoSaqueDAO.persiste(solicitacaoSaque);

        res.setCodigo(solicitacaoSaque.getCodigo());
    }

}
