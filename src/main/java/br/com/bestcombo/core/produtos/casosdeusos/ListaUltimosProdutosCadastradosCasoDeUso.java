package br.com.bestcombo.core.produtos.casosdeusos;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.produtos.dto.ProdutoDTO;
import br.com.bestcombo.core.produtos.dto.listaprodutos.ListaUltimosProdutosCadastradosRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.listaprodutos.ListaUltimosProdutosCadastradosRespostaDTO;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.core.produtos.mapper.ProdutoMapper;
import br.com.bestcombo.ports.dao.ProdutoDAO;

@ApplicationScoped
@RequiredArgsConstructor
@CasoDeUso(CasosDeUso.LISTA_ULTIMOS_PRODUTOS_CADASTRADOS)
public class ListaUltimosProdutosCadastradosCasoDeUso extends AbstractCasoDeUso<ListaUltimosProdutosCadastradosRequisicaoDTO, ListaUltimosProdutosCadastradosRespostaDTO> {

    final ProdutoDAO produtoDAO;

    @Override
    protected void processa(ListaUltimosProdutosCadastradosRequisicaoDTO req, ListaUltimosProdutosCadastradosRespostaDTO res) throws NegocioException {
        Collection<ProdutoEntity> entities = produtoDAO.listaUltimosProdutosCadastrados();

        List<ProdutoDTO> produtos = entities.stream()
                .map(ProdutoMapper::mapperParaDTO)
                .collect(Collectors.toList());

        res.setProdutos(produtos);
    }

}
