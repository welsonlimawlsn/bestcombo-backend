package br.com.bestcombo.core.categoria.mapper;

import br.com.bestcombo.core.categoria.dto.CategoriaDTO;
import br.com.bestcombo.core.categoria.entity.CategoriaEntity;

public class CategoriaMapper {

    public static CategoriaDTO mapperParaDTO(CategoriaEntity categoriaEntity) {
        return CategoriaDTO.builder()
                .codigo(categoriaEntity.getCodigo())
                .nome(categoriaEntity.getNome())
                .tipoServico(categoriaEntity.getTipoServico())
                .build();
    }

}
