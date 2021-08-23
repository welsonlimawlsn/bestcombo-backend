package br.com.bestcombo.core.enderecos.modelos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Endereco {

    private String endereco;

    private String cidade;

    private String bairro;

    private String estado;

    private String cep;

}
