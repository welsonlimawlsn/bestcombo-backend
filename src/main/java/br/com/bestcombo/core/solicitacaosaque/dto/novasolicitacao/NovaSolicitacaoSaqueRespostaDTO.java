package br.com.bestcombo.core.solicitacaosaque.dto.novasolicitacao;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;

@Getter
@Setter
public class NovaSolicitacaoSaqueRespostaDTO extends RespostaDTO {

    private UUID codigo;

}
