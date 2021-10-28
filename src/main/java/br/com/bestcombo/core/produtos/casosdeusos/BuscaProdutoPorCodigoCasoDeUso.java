package br.com.bestcombo.core.produtos.casosdeusos;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.produtos.dto.buscaproduto.BuscaProdutoPorCodigoRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.buscaproduto.BuscaProdutoPorCodigoRespostaDTO;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.core.produtos.mapper.ProdutoMapper;
import br.com.bestcombo.ports.dao.ProdutoDao;

@CasoDeUso(CasosDeUso.BUSCA_PRODUTO_POR_CODIGO)
@ApplicationScoped
public class BuscaProdutoPorCodigoCasoDeUso extends AbstractCasoDeUso<BuscaProdutoPorCodigoRequisicaoDTO, BuscaProdutoPorCodigoRespostaDTO> {

    @Inject
    ProdutoDao produtoDao;

    @Override
    protected void processa(BuscaProdutoPorCodigoRequisicaoDTO requisicao, BuscaProdutoPorCodigoRespostaDTO resposta) throws NegocioException {
        ProdutoEntity produtoEntity = produtoDao.buscaPorId(requisicao.getCodigo())
                .orElseThrow(() -> new NegocioException(Erro.PRODUTO_NAO_ENCONTRADO));

        resposta.setProduto(ProdutoMapper.mapperParaDTO(produtoEntity));
    }

}
