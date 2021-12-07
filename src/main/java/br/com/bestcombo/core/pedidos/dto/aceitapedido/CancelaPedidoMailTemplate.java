package br.com.bestcombo.core.pedidos.dto.aceitapedido;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import br.com.bestcombo.adapters.email.anotacao.Layout;
import br.com.bestcombo.adapters.email.dto.EmailTemplate;

@Layout("cancela_pedido_email")
@SuperBuilder
@Getter
@Setter
public class CancelaPedidoMailTemplate extends EmailTemplate {

    private String nomeUsuario;

    private String codigoPedido;

    private String motivoCancelamento;
}
