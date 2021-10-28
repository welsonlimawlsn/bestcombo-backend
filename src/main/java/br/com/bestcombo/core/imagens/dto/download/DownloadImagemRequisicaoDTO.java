package br.com.bestcombo.core.imagens.dto.download;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.PathParam;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.pessoas.enums.TipoPessoa;
import br.com.bestcombo.core.seguranca.anotacao.Autoriza;

@Getter
@Setter
@CasoDeUso(CasosDeUso.DOWNLOAD_IMAGEM)
@Autoriza(TipoPessoa.PUBLICO)
public class DownloadImagemRequisicaoDTO extends RequisicaoDTO<DownloadImagemRespostaDTO> {

    @PathParam("nome")
    @NotEmpty
    private String nome;

}
