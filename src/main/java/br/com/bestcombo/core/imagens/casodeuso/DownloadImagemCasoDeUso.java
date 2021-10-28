package br.com.bestcombo.core.imagens.casodeuso;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.imagens.dto.download.DownloadImagemRequisicaoDTO;
import br.com.bestcombo.core.imagens.dto.download.DownloadImagemRespostaDTO;
import br.com.bestcombo.ports.service.ArquivoService;

@CasoDeUso(CasosDeUso.DOWNLOAD_IMAGEM)
@ApplicationScoped
public class DownloadImagemCasoDeUso extends AbstractCasoDeUso<DownloadImagemRequisicaoDTO, DownloadImagemRespostaDTO> {

    @Inject
    ArquivoService arquivoService;

    @Override
    protected void processa(DownloadImagemRequisicaoDTO requisicao, DownloadImagemRespostaDTO resposta) throws NegocioException {

        Map<String, Object> arquivo = arquivoService.download(requisicao.getNome());

        resposta.setArquivo((byte[]) arquivo.get(ArquivoService.ARQUIVO));
        resposta.setTipo((String) arquivo.get(ArquivoService.TIPO_ARQUIVO));
        resposta.setNome((String) arquivo.get(ArquivoService.NOME_ARQUIVO));

    }

}
