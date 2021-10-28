package br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Documento {

    @JsonProperty("type")
    private String tipo;

    @JsonProperty("number")
    private String numero;

}
