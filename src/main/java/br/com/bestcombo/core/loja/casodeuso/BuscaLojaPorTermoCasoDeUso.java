package br.com.bestcombo.core.loja.casodeuso;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.dto.buscalojaportermo.BuscaLojaPorTermoRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.buscalojaportermo.BuscaLojaPorTermoRespostaDTO;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.loja.mapper.LojaMapper;
import br.com.bestcombo.ports.dao.LojaDAO;

@ApplicationScoped
@CasoDeUso(CasosDeUso.BUSCA_LOJA_POR_TERMO)
public class BuscaLojaPorTermoCasoDeUso extends AbstractCasoDeUso<BuscaLojaPorTermoRequisicaoDTO, BuscaLojaPorTermoRespostaDTO> {

    @Inject
    LojaDAO lojaDAO;

    @Override
    protected void processa(BuscaLojaPorTermoRequisicaoDTO requisicao, BuscaLojaPorTermoRespostaDTO resposta) throws NegocioException {
        Collection<LojaEntity> lojas = lojaDAO.buscaLojaPorTermo(requisicao.getTermo());

        resposta.setLojas(LojaMapper.mapperParaDTO(lojas));
    }

}
