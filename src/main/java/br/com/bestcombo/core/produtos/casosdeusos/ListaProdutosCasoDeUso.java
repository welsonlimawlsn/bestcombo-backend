package br.com.bestcombo.core.produtos.casosdeusos;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.produtos.dto.listaprodutos.ListaProdutosRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.listaprodutos.ListaProdutosRespostaDTO;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.core.produtos.mapper.ProdutoMapper;
import br.com.bestcombo.ports.dao.ProdutoDAO;

@CasoDeUso(CasosDeUso.LISTA_PRODUTOS)
@ApplicationScoped
public class ListaProdutosCasoDeUso extends AbstractCasoDeUso<ListaProdutosRequisicaoDTO, ListaProdutosRespostaDTO> {

    @Inject
    ProdutoDAO produtoDao;

    @Override
    protected void processa(ListaProdutosRequisicaoDTO requisicao, ListaProdutosRespostaDTO resposta) throws NegocioException {
        Collection<ProdutoEntity> produtos = produtoDao.lista(requisicao.getQueryParameters());

        resposta.setProdutos(produtos.stream().map(ProdutoMapper::mapperParaDTO).collect(Collectors.toList()));
    }

}
