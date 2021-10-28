package br.com.bestcombo.core.pedidos.dto.aceitapedido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.ALTERA_SITUACAO_PEDIDO)
public class AlteraSituacaoPedidoRequisicaoDTO extends RequisicaoDTO<AlteraSituacaoPedidoRespostaDTO> {

    @NotNull(message = "O código do pedido é obrigatório")
    @JsonIgnore
    private UUID codigoPedido;

    @NotNull(message = "A situacao do pedido é obrigatória")
    @Pattern(regexp = "PARCEIRO_ACEITA|PARCEIRO_RECUSA|PARCEIRO_CONCLUI", message = "Valor inválido para resposta")
    private String respostaParceiro;

    private String motivoCancelamento;

}
