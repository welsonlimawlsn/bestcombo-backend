package br.com.bestcombo.core.loja.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LojaDTO {

    private UUID codigo;

    private String cnpj;

    private String nome;

}
