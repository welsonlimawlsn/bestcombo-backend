package br.com.bestcombo.core.loja.casodeuso;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.dto.adicionachavepix.AdicionaChavePixRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.adicionachavepix.AdicionaChavePixRespostaDTO;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.ports.dao.LojaDAO;
import br.com.bestcombo.ports.service.SegurancaService;

@RequiredArgsConstructor
@ApplicationScoped
@CasoDeUso(CasosDeUso.ADICIONA_CHAVE_PIX)
public class AdicionaChavePixLojaCasoDeUso extends AbstractCasoDeUso<AdicionaChavePixRequisicaoDTO, AdicionaChavePixRespostaDTO> {

    final LojaDAO lojaDAO;

    final SegurancaService segurancaService;

    @Override
    protected void processa(AdicionaChavePixRequisicaoDTO req, AdicionaChavePixRespostaDTO res) throws NegocioException {
        LojaEntity lojaEntity = lojaDAO.buscaLojaPorParceiro(segurancaService.getCodigoUsuarioLogado()).orElseThrow();

        lojaEntity.setChavePix(req.getChavePix());
    }

}
