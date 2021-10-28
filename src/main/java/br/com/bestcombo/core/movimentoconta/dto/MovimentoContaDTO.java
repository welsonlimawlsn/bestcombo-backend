package br.com.bestcombo.core.movimentoconta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import br.com.bestcombo.core.movimentoconta.enums.TipoMovimentoConta;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimentoContaDTO {

    private UUID codigo;

    private UUID codigoPedido;

    private TipoMovimentoConta tipoMovimento;

    private BigDecimal valor;

    private ZonedDateTime dataHora;

    private ZonedDateTime dataHoraEfetivacao;

    private Boolean efetivado;

    private String descricao;

}
