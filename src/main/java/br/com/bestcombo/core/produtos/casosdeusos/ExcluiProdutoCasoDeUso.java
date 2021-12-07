package br.com.bestcombo.core.produtos.casosdeusos;

import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.produtos.dto.excluiproduto.ExcluiProdutoRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.excluiproduto.ExcluiProdutoRespostaDTO;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.ports.dao.ProdutoDAO;
import br.com.bestcombo.ports.service.SegurancaService;

@ApplicationScoped
@RequiredArgsConstructor
@CasoDeUso(CasosDeUso.EXCLUI_PRODUTO)
public class ExcluiProdutoCasoDeUso extends AbstractCasoDeUso<ExcluiProdutoRequisicaoDTO, ExcluiProdutoRespostaDTO> {

    final ProdutoDAO produtoDao;

    final SegurancaService segurancaService;

    @Override
    protected void processa(ExcluiProdutoRequisicaoDTO req, ExcluiProdutoRespostaDTO res) throws NegocioException {
        ProdutoEntity produtoEntity = produtoDao.buscaPorIdEParceiro(req.getCodigo(), segurancaService.getCodigoUsuarioLogado()).orElseThrow();

        produtoEntity.desativa();
    }

}
