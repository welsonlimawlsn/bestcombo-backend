package br.com.bestcombo.core.produtos.casosdeusos;

import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.categoria.entity.CategoriaEntity;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.produtos.dto.novoproduto.NovoProdutoRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.novoproduto.NovoProdutoRespostaDTO;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;
import br.com.bestcombo.ports.dao.LojaDAO;
import br.com.bestcombo.ports.dao.ProdutoDao;
import br.com.bestcombo.ports.service.SegurancaService;

@CasoDeUso(CasosDeUso.CADASTRO_PRODUTO)
@ApplicationScoped
public class NovoProdutoCasosDeUso extends AbstractCasoDeUso<NovoProdutoRequisicaoDTO, NovoProdutoRespostaDTO> {

    @Inject
    ProdutoDao produtoDao;

    @Inject
    LojaDAO lojaDAO;

    @Inject
    SegurancaService segurancaService;

    @Override
    protected void processa(NovoProdutoRequisicaoDTO requisicaoDTO, NovoProdutoRespostaDTO novoProdutoRespostaDTO) throws NegocioException {

        var loja = buscaLojaParceiro(requisicaoDTO);

        ProdutoEntity produtoEntity = novoProdutoEntity(requisicaoDTO, loja);

        produtoDao.persiste(produtoEntity);

        mapperParaResposta(novoProdutoRespostaDTO, produtoEntity);
    }

    private void mapperParaResposta(NovoProdutoRespostaDTO novoProdutoRespostaDTO, ProdutoEntity produtoEntity) {
        novoProdutoRespostaDTO.setCodigo(produtoEntity.getCodigo());
        novoProdutoRespostaDTO.setNome(produtoEntity.getNome());
        novoProdutoRespostaDTO.setDescricao(produtoEntity.getDescricao());
        novoProdutoRespostaDTO.setPreco(produtoEntity.getPreco());
    }

    private LojaEntity buscaLojaParceiro(NovoProdutoRequisicaoDTO requisicaoDTO) throws NegocioException {
        return lojaDAO.buscaLojaPorCodigoEParceiro(requisicaoDTO.getCodigoLoja(), segurancaService.getCodigoUsuarioLogado()).orElseThrow(() -> new NegocioException(Erro.LOJA_INVALIDA));
    }

    private ProdutoEntity novoProdutoEntity(NovoProdutoRequisicaoDTO requisicaoDTO, LojaEntity loja) {
        var produtoEntity = new ProdutoEntity();
        var categorias = requisicaoDTO.getCategorias().stream()
                .map(codigo -> CategoriaEntity.builder()
                        .codigo(codigo)
                        .build())
                .collect(Collectors.toSet());

        produtoEntity.setNome(requisicaoDTO.getNome());
        produtoEntity.setDescricao(requisicaoDTO.getDescricao());
        produtoEntity.setPreco(requisicaoDTO.getPreco());
        produtoEntity.setLoja(loja);
        produtoEntity.setQuantidadePessoas(requisicaoDTO.getQuantidadePessoas());
        produtoEntity.setImagem(requisicaoDTO.getImagem());
        produtoEntity.setCategorias(categorias);

        return produtoEntity;
    }

}
