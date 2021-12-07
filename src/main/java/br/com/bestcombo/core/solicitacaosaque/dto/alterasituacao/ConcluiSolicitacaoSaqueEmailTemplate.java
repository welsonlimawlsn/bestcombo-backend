package br.com.bestcombo.core.solicitacaosaque.dto.alterasituacao;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import br.com.bestcombo.adapters.email.anotacao.Layout;
import br.com.bestcombo.adapters.email.dto.EmailTemplate;

@SuperBuilder
@Getter
@Setter
@Layout("conclui_solicitacao_saque_email")
public class ConcluiSolicitacaoSaqueEmailTemplate extends EmailTemplate {

    private String nomeParceiro;
}
