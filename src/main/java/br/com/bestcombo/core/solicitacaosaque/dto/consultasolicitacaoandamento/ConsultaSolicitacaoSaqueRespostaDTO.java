package br.com.bestcombo.core.solicitacaosaque.dto.consultasolicitacaoandamento;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.solicitacaosaque.enums.SituacaoSolicitacaoSaque;

@Getter
@Setter
public class ConsultaSolicitacaoSaqueRespostaDTO extends RespostaDTO {

    private UUID codigo;

    private SituacaoSolicitacaoSaque situacaoSolicitacaoSaque;

    private ZonedDateTime dataCadastro;

    private ZonedDateTime dataUltimaAtualizacao;

    private BigDecimal valor;

}
