package br.com.bestcombo.core.pedidos.dto.novopedido;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.NOVO_PEDIDO)
public class NovoPedidoRequisicaoDTO extends RequisicaoDTO<NovoPedidoRespostaDTO> {

    @NotNull(message = "Deve informar o código do estabelecimento")
    private UUID codigoEstabelecimento;

    @NotNull(message = "Deve informar os produtos do pedido")
    @Size(min = 1, message = "Deve ter ao menos um produto no pedido")
    @Valid
    private List<ProdutoPedidoDTO> produtos;

    @NotNull(message = "Deve informar a data de agendamento")
    @Future(message = "Deve informar uma data de agendamento futuro")
    private ZonedDateTime dataAgendamento;

    private String observacao;

    @NotNull(message = "O cartão é obrigatório")
    @Valid
    private CartaoDTO cartao;

}
