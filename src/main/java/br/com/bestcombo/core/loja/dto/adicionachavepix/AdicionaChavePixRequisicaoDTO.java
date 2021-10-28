package br.com.bestcombo.core.loja.dto.adicionachavepix;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Getter
@Setter
@CasoDeUso(CasosDeUso.ADICIONA_CHAVE_PIX)
public class AdicionaChavePixRequisicaoDTO extends RequisicaoDTO<AdicionaChavePixRespostaDTO> {

    @NotEmpty(message = "A chave PIX é obrigatória")
    private String chavePix;

}
