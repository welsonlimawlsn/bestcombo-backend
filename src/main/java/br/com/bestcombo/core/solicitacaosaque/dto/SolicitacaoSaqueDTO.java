package br.com.bestcombo.core.solicitacaosaque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import br.com.bestcombo.core.solicitacaosaque.enums.SituacaoSolicitacaoSaque;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitacaoSaqueDTO {

    private UUID codigo;

    private SituacaoSolicitacaoSaque situacaoSolicitacaoSaque;

    private ZonedDateTime dataCadastro;

    private ZonedDateTime dataUltimaAtualizacao;

    private BigDecimal valor;

    private String chavePix;

}
