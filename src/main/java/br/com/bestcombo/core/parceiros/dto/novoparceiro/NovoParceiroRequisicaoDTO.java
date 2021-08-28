package br.com.bestcombo.core.parceiros.dto.novoparceiro;

import lombok.Getter;
import lombok.Setter;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.pessoas.dto.novapessoa.NovaPessoaRequisicaoDTO;

@CasoDeUso(CasosDeUso.NOVO_PARCEIRO)
@Getter
@Setter
public class NovoParceiroRequisicaoDTO extends NovaPessoaRequisicaoDTO<NovoParceiroRespostaDTO> {

}
