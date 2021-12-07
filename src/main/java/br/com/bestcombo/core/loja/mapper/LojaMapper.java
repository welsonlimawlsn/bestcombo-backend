package br.com.bestcombo.core.loja.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bestcombo.core.categoria.mapper.CategoriaMapper;
import br.com.bestcombo.core.loja.dto.LojaDTO;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.produtos.dto.ProdutoDTO;
import br.com.bestcombo.core.produtos.mapper.ProdutoMapper;

public class LojaMapper {

    public static LojaDTO mapperParaDTO(LojaEntity lojaEntity) {
        return mapperParaDTO(lojaEntity, false);
    }

    public static LojaDTO mapperParaDTO(LojaEntity lojaEntity, boolean mapperProdutos) {
        return LojaDTO.builder()
                .cnpj(lojaEntity.getCnpj())
                .codigo(lojaEntity.getCodigo())
                .nome(lojaEntity.getNome())
                .descricao(lojaEntity.getDescricao())
                .imagem(lojaEntity.getImagem())
                .produtos(mapperProdutos ? mapperParaProdutosDTO(lojaEntity) : null)
                .build();
    }

    private static List<ProdutoDTO> mapperParaProdutosDTO(LojaEntity lojaEntity) {
        return lojaEntity.getProdutos().stream()
                .map(entity -> ProdutoDTO.builder()
                        .codigo(entity.getCodigo())
                        .nome(entity.getNome())
                        .descricao(entity.getDescricao())
                        .preco(entity.getPreco())
                        .quantidadePessoas(entity.getQuantidadePessoas())
                        .imagem(entity.getImagem())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<LojaDTO> mapperParaDTO(Collection<LojaEntity> entities) {
        return mapperParaDTO(entities, false);
    }

    public static List<LojaDTO> mapperParaDTO(Collection<LojaEntity> entities, boolean mapperProdutos) {
        return entities.stream()
                .map(lojaEntity -> mapperParaDTO(lojaEntity, mapperProdutos))
                .collect(Collectors.toList());
    }

}
