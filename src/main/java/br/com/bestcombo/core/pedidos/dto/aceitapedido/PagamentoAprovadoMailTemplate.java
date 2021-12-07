package br.com.bestcombo.core.pedidos.dto.aceitapedido;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import br.com.bestcombo.adapters.email.anotacao.Layout;
import br.com.bestcombo.adapters.email.dto.EmailTemplate;

@Layout("pagamento_aprovado_email")
@SuperBuilder
@Getter
@Setter
public class PagamentoAprovadoMailTemplate extends EmailTemplate {

    private String nomeCliente;

    private String codigoPedido;

}
