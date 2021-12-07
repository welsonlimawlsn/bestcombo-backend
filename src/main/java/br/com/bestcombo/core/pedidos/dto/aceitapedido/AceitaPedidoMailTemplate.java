package br.com.bestcombo.core.pedidos.dto.aceitapedido;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import br.com.bestcombo.adapters.email.anotacao.Layout;
import br.com.bestcombo.adapters.email.dto.EmailTemplate;

@Layout("aceita_pedido_email")
@SuperBuilder
@Getter
@Setter
public class AceitaPedidoMailTemplate extends EmailTemplate {

    private String nomeCliente;

    private String codigoPedido;

}
