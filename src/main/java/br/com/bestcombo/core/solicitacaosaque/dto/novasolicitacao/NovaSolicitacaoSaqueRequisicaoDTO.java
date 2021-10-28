package br.com.bestcombo.core.solicitacaosaque.dto.novasolicitacao;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.NOVA_SOLICITACAO_SAQUE)
public class NovaSolicitacaoSaqueRequisicaoDTO extends RequisicaoDTO<NovaSolicitacaoSaqueRespostaDTO> {

    @NotNull(message = "O valor é obrigatório")
    @Min(value = 1, message = "O valor deve ser de no minimo 1 real")
    private BigDecimal valor;

}
