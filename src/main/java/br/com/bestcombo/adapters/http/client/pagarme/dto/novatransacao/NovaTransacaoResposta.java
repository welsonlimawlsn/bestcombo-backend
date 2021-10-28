package br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovaTransacaoResposta {

    @JsonProperty("id")
    private String codigoTransancao;

    @JsonProperty("status")
    private String situacaoTransancao;
}
