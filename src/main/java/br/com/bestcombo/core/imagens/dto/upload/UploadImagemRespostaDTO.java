package br.com.bestcombo.core.imagens.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadImagemRespostaDTO extends RespostaDTO {

    private String nomeArquivo;

}
