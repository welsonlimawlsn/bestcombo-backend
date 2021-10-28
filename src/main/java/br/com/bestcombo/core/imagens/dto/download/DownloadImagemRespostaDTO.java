package br.com.bestcombo.core.imagens.dto.download;

import lombok.Getter;
import lombok.Setter;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;

@Getter
@Setter
public class DownloadImagemRespostaDTO extends RespostaDTO {

    private byte[] arquivo;

    private String tipo;

    private String nome;

}
