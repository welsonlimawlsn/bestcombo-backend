package br.com.bestcombo.adapters.http.client.pagarme.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.QueryParam;

@Getter
@Setter
public class RequisicaoPagarMe {

    @QueryParam("api_key")
    @JsonProperty("api_key")
    private String chaveApi;

}
