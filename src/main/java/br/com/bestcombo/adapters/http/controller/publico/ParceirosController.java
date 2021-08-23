package br.com.bestcombo.adapters.http.controller.publico;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRequisicaoDTO;
import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRespostaDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/publico/parceiros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParceirosController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @POST
    public Response novoParceiro(NovoParceiroRequisicaoDTO requisicao) throws NegocioException {
        NovoParceiroRespostaDTO respostaDTO = processadorCasoDeUso.processa(requisicao);

        return Response.created(URI.create("/parceiros/" + respostaDTO.getCodigo())).build();
    }

}
