package br.com.bestcombo.core.categoria.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoriaDTO {

    private Integer codigo;

    private String nome;

    private Integer tipoServico;

}
