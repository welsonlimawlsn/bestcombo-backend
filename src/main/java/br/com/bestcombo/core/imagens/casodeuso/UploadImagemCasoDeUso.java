package br.com.bestcombo.core.imagens.casodeuso;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.imagens.dto.upload.UploadImagemRequisicaoDTO;
import br.com.bestcombo.core.imagens.dto.upload.UploadImagemRespostaDTO;
import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.service.ArquivoService;

@ApplicationScoped
@CasoDeUso(CasosDeUso.UPLOAD_IMAGEM)
public class UploadImagemCasoDeUso extends AbstractCasoDeUso<UploadImagemRequisicaoDTO, UploadImagemRespostaDTO> {

    @Inject
    ArquivoService arquivoService;

    @Override
    protected void processa(UploadImagemRequisicaoDTO requisicao, UploadImagemRespostaDTO resposta) throws NegocioException {
        arquivoService.upload(requisicao.getArquivo(), requisicao.getTipo(), requisicao.getNome());

        resposta.setNomeArquivo(requisicao.getNome());
    }

}
