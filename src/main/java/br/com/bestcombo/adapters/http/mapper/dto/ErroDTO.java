package br.com.bestcombo.adapters.http.mapper.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErroDTO {

    private Integer codigoInterno;

    private List<String> mensagens;

    private Integer codigoHttp;

    private String descricaoCodigoHttp;

}
