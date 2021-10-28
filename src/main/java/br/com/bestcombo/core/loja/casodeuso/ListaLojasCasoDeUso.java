package br.com.bestcombo.core.loja.casodeuso;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.dto.listalojas.ListaLojasRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.listalojas.ListaLojasRespostaDTO;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.loja.mapper.LojaMapper;
import br.com.bestcombo.ports.dao.LojaDAO;

@CasoDeUso(CasosDeUso.LISTA_LOJAS)
@ApplicationScoped
public class ListaLojasCasoDeUso extends AbstractCasoDeUso<ListaLojasRequisicaoDTO, ListaLojasRespostaDTO> {

    @Inject
    LojaDAO lojaDAO;

    @Override
    protected void processa(ListaLojasRequisicaoDTO requisicao, ListaLojasRespostaDTO resposta) throws NegocioException {
        Collection<LojaEntity> lista = lojaDAO.lista(requisicao.getQueryParameters());

        resposta.setLojas(LojaMapper.mapperParaDTO(lista));
    }

}
