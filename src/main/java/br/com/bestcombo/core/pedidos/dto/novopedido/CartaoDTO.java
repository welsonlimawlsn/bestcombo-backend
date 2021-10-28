package br.com.bestcombo.core.pedidos.dto.novopedido;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CartaoDTO {

    @NotEmpty(message = "Vencimento do Cartão é obrigatório")
    private String dataVencimento;

    @NotEmpty(message = "O número do cartão é obrigatório")
    private String numero;

    @NotEmpty(message = "O código de segurança é obrigatório")
    private String cvv;

    @NotEmpty(message = "O nome no cartão é obrigatório")
    private String nome;

}
