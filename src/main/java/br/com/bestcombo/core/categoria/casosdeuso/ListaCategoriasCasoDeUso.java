package br.com.bestcombo.core.categoria.casosdeuso;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.categoria.dto.CategoriaDTO;
import br.com.bestcombo.core.categoria.dto.listacategorias.ListaCategoriasRequisicaoDTO;
import br.com.bestcombo.core.categoria.dto.listacategorias.ListaCategoriasRespostaDTO;
import br.com.bestcombo.core.categoria.entity.CategoriaEntity;
import br.com.bestcombo.core.categoria.mapper.CategoriaMapper;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.dao.CategoriaDAO;

@ApplicationScoped
@CasoDeUso(CasosDeUso.LISTA_CATEGORIAS)
public class ListaCategoriasCasoDeUso extends AbstractCasoDeUso<ListaCategoriasRequisicaoDTO, ListaCategoriasRespostaDTO> {

    @Inject
    CategoriaDAO categoriaDAO;

    @Override
    protected void processa(ListaCategoriasRequisicaoDTO requisicao, ListaCategoriasRespostaDTO resposta) throws NegocioException {
        Collection<CategoriaEntity> categorias = categoriaDAO.lista();

        List<CategoriaDTO> dtos = mapperParaDTO(categorias);

        resposta.setCategorias(dtos);
    }

    private List<CategoriaDTO> mapperParaDTO(Collection<CategoriaEntity> enitties) {
        return enitties.stream()
                .map(CategoriaMapper::mapperParaDTO)
                .collect(Collectors.toList());
    }

}
