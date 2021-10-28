package br.com.bestcombo.core.enderecos.mapper;

import br.com.bestcombo.core.enderecos.dto.EnderecoDTO;
import br.com.bestcombo.core.enderecos.entity.EnderecoPessoaEntity;

public class EnderecoPessoaMapper {

    public static EnderecoDTO mapperParaDTO(EnderecoPessoaEntity entity) {
        return EnderecoDTO.builder()
                .cep(entity.getCep())
                .bairro(entity.getBairro())
                .cidade(entity.getCidade())
                .complemento(entity.getComplemento())
                .estado(entity.getEstado())
                .numero(entity.getNumero())
                .rua(entity.getRua())
                .build();
    }

}
