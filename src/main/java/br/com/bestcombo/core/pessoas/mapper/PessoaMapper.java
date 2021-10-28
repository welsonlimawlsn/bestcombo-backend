package br.com.bestcombo.core.pessoas.mapper;

import br.com.bestcombo.core.pessoas.dto.PessoaDTO;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;

public class PessoaMapper {

    public static PessoaDTO mapperParaDTO(PessoaEntity entity) {
        return PessoaDTO.builder()
                .codigo(entity.getCodigo())
                .cpf(entity.getCpf())
                .email(entity.getEmail())
                .nome(entity.getNome())
                .sobrenome(entity.getSobrenome())
                .build();
    }

}
