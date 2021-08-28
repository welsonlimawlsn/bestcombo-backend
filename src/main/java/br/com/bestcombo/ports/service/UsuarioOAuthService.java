package br.com.bestcombo.ports.service;

import br.com.bestcombo.core.pessoas.dto.novapessoa.NovaPessoaRequisicaoDTO;
import br.com.bestcombo.core.pessoas.dto.novapessoa.NovaPessoaRespostaDTO;

public interface UsuarioOAuthService {

    String criaNovoUsuario(NovaPessoaRequisicaoDTO<? extends NovaPessoaRespostaDTO> requisicao, String grupo);

}
