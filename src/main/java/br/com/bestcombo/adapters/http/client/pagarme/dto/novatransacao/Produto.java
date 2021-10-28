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
public class Produto {

    @JsonProperty("id")
    private String codigo;

    @JsonProperty("title")
    private String nome;

    @JsonProperty("unit_price")
    private Long valorUnitario;

    @JsonProperty("quantity")
    private Integer quantidade;

    @JsonProperty("tangible")
    private Boolean produtoFisico;

}
