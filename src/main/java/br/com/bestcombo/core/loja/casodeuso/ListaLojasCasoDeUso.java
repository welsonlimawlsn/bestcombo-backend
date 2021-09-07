package br.com.bestcombo.core.loja.casodeuso;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.dto.LojaDTO;
import br.com.bestcombo.core.loja.dto.listalojas.ListaLojasRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.listalojas.ListaLojasRespostaDTO;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.ports.dao.LojaDAO;

@CasoDeUso(CasosDeUso.LISTA_LOJAS)
@ApplicationScoped
public class ListaLojasCasoDeUso extends AbstractCasoDeUso<ListaLojasRequisicaoDTO, ListaLojasRespostaDTO> {

    @Inject
    LojaDAO lojaDAO;

    @Override
    protected void processa(ListaLojasRequisicaoDTO requisicao, ListaLojasRespostaDTO resposta) throws NegocioException {
        List<LojaEntity> lista = lojaDAO.lista(requisicao.getQueryParameters());

        resposta.setLojas(mapperParaDTO(lista));
    }

    private List<LojaDTO> mapperParaDTO(List<LojaEntity> entities) {
        return entities.stream()
                .map(e -> LojaDTO.builder()
                        .cnpj(e.getCnpj())
                        .codigo(e.getCodigo())
                        .nome(e.getNome())
                        .build()
                )
                .collect(Collectors.toList());
    }

}
