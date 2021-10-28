package br.com.bestcombo.adapters.http.client.pagarme.dto.novocartao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.bestcombo.adapters.http.client.pagarme.dto.RequisicaoPagarMe;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NovoCartaoRequisicao extends RequisicaoPagarMe {

    @JsonProperty("card_expiration_date")
    private String dataVencimento;

    @JsonProperty("card_number")
    private String numero;

    @JsonProperty("card_cvv")
    private String cvv;

    @JsonProperty("card_holder_name")
    private String nome;

}
