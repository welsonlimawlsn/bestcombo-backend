package br.com.bestcombo.core.loja.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bestcombo.core.loja.dto.LojaDTO;
import br.com.bestcombo.core.loja.entity.LojaEntity;

public class LojaMapper {

    public static LojaDTO mapperParaDTO(LojaEntity lojaEntity) {
        return LojaDTO.builder()
                .cnpj(lojaEntity.getCnpj())
                .codigo(lojaEntity.getCodigo())
                .nome(lojaEntity.getNome())
                .descricao(lojaEntity.getDescricao())
                .imagem(lojaEntity.getImagem())
                .build();
    }

    public static List<LojaDTO> mapperParaDTO(Collection<LojaEntity> entities) {
        return entities.stream().map(LojaMapper::mapperParaDTO).collect(Collectors.toList());
    }

}
