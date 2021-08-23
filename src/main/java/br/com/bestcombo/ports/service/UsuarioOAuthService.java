package br.com.bestcombo.ports.service;

import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRequisicaoDTO;
import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRespostaDTO;

public interface UsuarioOAuthService {

    void criaNovoUsuario(NovoParceiroRequisicaoDTO requisicao, NovoParceiroRespostaDTO resposta);

}
