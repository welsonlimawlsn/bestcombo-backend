package br.com.bestcombo.core.imagens.dto.upload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;

@Builder
@Getter
@Setter
@CasoDeUso(CasosDeUso.UPLOAD_IMAGEM)
public class UploadImagemRequisicaoDTO extends RequisicaoDTO<UploadImagemRespostaDTO> {

    public static final int TAMANHO_1MB = 1048576;

    @Size(max = TAMANHO_1MB, message = "O tamanho máximo do arquivo excedido.")
    @NotNull
    private byte[] arquivo;

    @NotNull
    private String nome;

    @NotNull
    @Pattern(regexp = "image/(png|jpeg)", message = "Formato de arquivo inválido.")
    private String tipo;

}
