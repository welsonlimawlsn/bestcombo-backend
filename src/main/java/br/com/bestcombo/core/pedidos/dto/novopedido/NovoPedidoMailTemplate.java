package br.com.bestcombo.core.pedidos.dto.novopedido;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import br.com.bestcombo.adapters.email.anotacao.Layout;
import br.com.bestcombo.adapters.email.dto.EmailTemplate;

@Layout("novo_pedido_email")
@SuperBuilder
@Getter
@Setter
public class NovoPedidoMailTemplate extends EmailTemplate {

    private String nomeParceiro;

    private String codigoPedido;

}
