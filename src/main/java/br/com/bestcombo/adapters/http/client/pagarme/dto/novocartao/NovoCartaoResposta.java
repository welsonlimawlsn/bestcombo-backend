package br.com.bestcombo.adapters.http.client.pagarme.dto.novocartao;

/*
{
  "object": "card",
  "id": "card_cj428xxsx01dt3f6dvre6belx",
  "date_created": "2017-06-18T05:03:19.907Z",
  "date_updated": "2017-06-18T05:03:20.318Z",
  "brand": "visa",
  "holder_name": "Aardvark Silva",
  "first_digits": "401872",
  "last_digits": "8048",
  "country": "RU",
  "fingerprint": "TaApkY+9emV9",
  "customer": null,
  "valid": true,
  "expiration_date": "1122"
}
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NovoCartaoResposta {

    @JsonProperty("id")
    private String codigo;

}
