package br.com.bestcombo.core.loja.casodeuso;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.dto.buscalojaporparceiro.BuscaLojaPorParceiroRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.buscalojaporparceiro.BuscaLojaPorParceiroRespostaDTO;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.ports.dao.LojaDAO;
import br.com.bestcombo.ports.service.SegurancaService;

@CasoDeUso(CasosDeUso.BUSCA_LOJA_POR_PARCEIRO)
@ApplicationScoped
public class BuscaLojaPorParceiroCasoDeUso extends AbstractCasoDeUso<BuscaLojaPorParceiroRequisicaoDTO, BuscaLojaPorParceiroRespostaDTO> {

    @Inject
    SegurancaService segurancaService;

    @Inject
    LojaDAO lojaDAO;

    @Override
    protected void processa(BuscaLojaPorParceiroRequisicaoDTO requisicao, BuscaLojaPorParceiroRespostaDTO resposta) throws NegocioException {
        if (segurancaService.isParceiro()) {
            segurancaService.validaPessoaLogada(requisicao.getCodigoParceiro());
        }

        LojaEntity loja = lojaDAO.buscaLojaPorParceiro(requisicao.getCodigoParceiro())
                .orElseThrow(() -> new NegocioException(Erro.LOJA_NAO_ENCONTRADA));

        mapperParaDTO(loja, resposta);
    }

    private void mapperParaDTO(LojaEntity loja, BuscaLojaPorParceiroRespostaDTO resposta) {
        resposta.setCnpj(loja.getCnpj());
        resposta.setCodigo(loja.getCodigo().toString());
        resposta.setNome(loja.getNome());
    }

}
