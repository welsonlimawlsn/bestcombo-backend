package br.com.bestcombo.core.clientes.dto.cadastrocliente;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.pessoas.dto.novapessoa.NovaPessoaRequisicaoDTO;

@CasoDeUso(CasosDeUso.CADASTRO_CLIENTE)
public class CadastroClienteRequisicaoDTO extends NovaPessoaRequisicaoDTO<CadastroClienteRespostaDTO> {

}
