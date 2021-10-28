package br.com.bestcombo.adapters.http.controller.protegido;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.imagens.dto.download.DownloadImagemRequisicaoDTO;
import br.com.bestcombo.core.imagens.dto.download.DownloadImagemRespostaDTO;
import br.com.bestcombo.core.imagens.dto.upload.UploadImagemRequisicaoDTO;
import br.com.bestcombo.core.imagens.dto.upload.UploadImagemRespostaDTO;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/arquivos")
public class ArquivoController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/upload")
    public Response uploadArquivo(MultipartFormDataInput multipart) throws NegocioException {
        try {
            if (multipart.getParts().size() != 1) {
                throw new NegocioException(Erro.QUANTIDADE_ARQUIVOS_INVALIDA);
            }

            InputPart part = multipart.getParts().get(0);

            UploadImagemRequisicaoDTO requisicaoDTO = UploadImagemRequisicaoDTO.builder()
                    .tipo(part.getMediaType().toString())
                    .nome(UUID.randomUUID().toString().replace("-", "") + "." + part.getMediaType().getSubtype())
                    .arquivo(part.getBody(InputStream.class, null).readAllBytes())
                    .build();

            UploadImagemRespostaDTO resposta = processadorCasoDeUso.processa(requisicaoDTO);

            return Response.ok(resposta).build();
        } catch (IOException e) {
            throw new InfraestruturaException("Ocorreu um erro ao recuperar o arquivo", e);
        }
    }

}
