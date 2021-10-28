package br.com.bestcombo.core.produtos.mapper;

import java.util.stream.Collectors;

import br.com.bestcombo.core.categoria.mapper.CategoriaMapper;
import br.com.bestcombo.core.produtos.dto.ProdutoDTO;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;

public class ProdutoMapper {

    public static ProdutoDTO mapperParaDTO(ProdutoEntity produtoEntity) {
        return ProdutoDTO.builder()
                .codigo(produtoEntity.getCodigo())
                .nome(produtoEntity.getNome())
                .codigoLoja(produtoEntity.getLoja().getCodigo())
                .descricao(produtoEntity.getDescricao())
                .preco(produtoEntity.getPreco())
                .quantidadePessoas(produtoEntity.getQuantidadePessoas())
                .imagem(produtoEntity.getImagem())
                .categorias(produtoEntity.getCategorias().stream()
                        .map(CategoriaMapper::mapperParaDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

}
