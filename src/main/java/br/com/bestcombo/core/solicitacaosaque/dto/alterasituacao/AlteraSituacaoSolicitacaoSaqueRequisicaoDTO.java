package br.com.bestcombo.core.solicitacaosaque.dto.alterasituacao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.solicitacaosaque.enums.SituacaoSolicitacaoSaque;

@Getter
@Setter
@CasoDeUso(CasosDeUso.ALTERA_SITUACAO_SOLICITACAO_SAQUE)
public class AlteraSituacaoSolicitacaoSaqueRequisicaoDTO extends RequisicaoDTO<AlteraSituacaoSolicitacaoSaqueRespostaDTO> {

    @JsonIgnore
    @NotNull(message = "O código da solicitação é obrigatório")
    private UUID codigoSolicitacao;

    @NotNull(message = "A situação é obrigatória")
    private SituacaoSolicitacaoSaque situacaoSolicitacaoSaque;

}
