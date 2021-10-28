package br.com.bestcombo.core.solicitacaosaque.casosdeuso;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.solicitacaosaque.dto.consultasolicitacaoandamento.ConsultaSolicitacaoSaqueRequisicaoDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.consultasolicitacaoandamento.ConsultaSolicitacaoSaqueRespostaDTO;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;
import br.com.bestcombo.ports.dao.LojaDAO;
import br.com.bestcombo.ports.dao.SolicitacaoSaqueDAO;
import br.com.bestcombo.ports.service.SegurancaService;

@RequiredArgsConstructor
@CasoDeUso(CasosDeUso.CONSULTA_SOLICITACAO_SAQUE_ANDAMENTO)
@ApplicationScoped
public class ConsultaSolicitacaoSaqueAndamentoCasoDeUso extends AbstractCasoDeUso<ConsultaSolicitacaoSaqueRequisicaoDTO, ConsultaSolicitacaoSaqueRespostaDTO> {

    final SolicitacaoSaqueDAO solicitacaoSaqueDAO;

    final SegurancaService segurancaService;

    final LojaDAO lojaDAO;

    @Override
    protected void processa(ConsultaSolicitacaoSaqueRequisicaoDTO req, ConsultaSolicitacaoSaqueRespostaDTO res) throws NegocioException {
        UUID codigoUsuarioLogado = segurancaService.getCodigoUsuarioLogado();

        LojaEntity lojaEntity = lojaDAO.buscaLojaPorParceiro(codigoUsuarioLogado).orElseThrow();

        SolicitacaoSaqueEntity solicitacaoSaqueEntity = solicitacaoSaqueDAO.buscaSolicitacaoPendentePorLoja(lojaEntity)
                .orElseThrow(() -> new NegocioException(Erro.NENHUMA_SOLICITACAO_EM_ANDAMENTO));

        res.setCodigo(solicitacaoSaqueEntity.getCodigo());
        res.setSituacaoSolicitacaoSaque(solicitacaoSaqueEntity.getSituacaoSolicitacaoSaque());
        res.setDataCadastro(solicitacaoSaqueEntity.getDataCadastro());
        res.setValor(solicitacaoSaqueEntity.getValor());
        res.setDataUltimaAtualizacao(solicitacaoSaqueEntity.getDataUltimaAtualizacao());
    }

}
