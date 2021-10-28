package br.com.bestcombo.core.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import br.com.bestcombo.core.loja.dto.LojaDTO;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedido;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {

    private UUID codigo;

    private LojaDTO loja;

    private ZonedDateTime data;

    private SituacaoPedido situacao;

    private BigDecimal valor;

    private ZonedDateTime dataAgendamento;

}
