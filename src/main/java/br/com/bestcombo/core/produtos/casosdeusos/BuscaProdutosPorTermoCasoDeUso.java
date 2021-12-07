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
import br.com.bestcombo.core.produtos.dto.buscaproduto.BuscaProdutosPorTermoRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.buscaproduto.BuscaProdutosPorTermoRespostaDTO;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.core.produtos.mapper.ProdutoMapper;
import br.com.bestcombo.ports.dao.ProdutoDAO;

@RequiredArgsConstructor
@ApplicationScoped
@CasoDeUso(CasosDeUso.BUSCA_PRODUTO_POR_TERMO)
public class BuscaProdutosPorTermoCasoDeUso extends AbstractCasoDeUso<BuscaProdutosPorTermoRequisicaoDTO, BuscaProdutosPorTermoRespostaDTO> {

    final ProdutoDAO produtoDAO;

    @Override
    protected void processa(BuscaProdutosPorTermoRequisicaoDTO req, BuscaProdutosPorTermoRespostaDTO res) throws NegocioException {
        Collection<ProdutoEntity> entities = produtoDAO.buscaProdutoPorTermo(req.getTermo());

        List<ProdutoDTO> produtos = entities.stream()
                .map(ProdutoMapper::mapperParaDTO)
                .collect(Collectors.toList());

        res.setProdutos(produtos);
    }

}
