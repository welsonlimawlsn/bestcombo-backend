package br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @JsonProperty("external_id")
    private String codigo;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("email")
    private String email;

    @JsonProperty("country")
    private String pais;

    @JsonProperty("type")
    private String tipo;

    @JsonProperty("documents")
    private List<Documento> documentos;

    @JsonProperty("phone_numbers")
    private List<String> telefones;

}
