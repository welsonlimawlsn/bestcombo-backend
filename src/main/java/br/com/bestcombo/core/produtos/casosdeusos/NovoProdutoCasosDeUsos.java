package br.com.bestcombo.core.produtos.casosdeusos;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.produtos.dto.novoproduto.NovoProdutoRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.novoproduto.NovoProdutoRespostaDTO;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.ports.dao.LojaDAO;
import br.com.bestcombo.ports.dao.ProdutoDao;
import br.com.bestcombo.ports.service.SegurancaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@CasoDeUso(CasosDeUso.CADASTRO_PRODUTO)
@ApplicationScoped
public class NovoProdutoCasosDeUsos extends AbstractCasoDeUso<NovoProdutoRequisicaoDTO, NovoProdutoRespostaDTO> {
    @Inject
    ProdutoDao produtoDao;
    @Inject
    LojaDAO lojaDAO;
@Inject
    SegurancaService segurancaService;
    @Override
    protected void processa(NovoProdutoRequisicaoDTO requisicaoDTO, NovoProdutoRespostaDTO novoProdutoRespostaDTO) throws NegocioException {

       var loja= lojaDAO.buscaLojaPorCodigoEParceiro(requisicaoDTO.getCodigoLoja(),segurancaService.getCodigoUsuarioLogado() ).orElseThrow(()->new NegocioException(Erro.LOJA_INVALIDA));
        var produtoEntity = new ProdutoEntity();
        produtoEntity.setNome(requisicaoDTO.getNome());
        produtoEntity.setDescricao(requisicaoDTO.getDescricao());
        produtoEntity.setPreco(requisicaoDTO.getPreco());
        produtoEntity.setLoja(loja);
        produtoDao.persiste(produtoEntity);
        novoProdutoRespostaDTO.setCodigo(produtoEntity.getCodigo());
        novoProdutoRespostaDTO.setNome(produtoEntity.getNome());
        novoProdutoRespostaDTO.setDescricao(produtoEntity.getDescricao());
        novoProdutoRespostaDTO.setPreco(produtoEntity.getPreco());
    }
}
