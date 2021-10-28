package br.com.bestcombo.adapters.http.controller.publico;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.imagens.dto.download.DownloadImagemRequisicaoDTO;
import br.com.bestcombo.core.imagens.dto.download.DownloadImagemRespostaDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/publico/arquivos")
public class ArquivoController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @GET
    @Path("/download/{nome}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadArquivo(@BeanParam DownloadImagemRequisicaoDTO requisicao) throws NegocioException {
        DownloadImagemRespostaDTO resposta = processadorCasoDeUso.processa(requisicao);

        return Response.ok(resposta.getArquivo()).header(HttpHeaders.CONTENT_TYPE, resposta.getTipo()).build();
    }

}
