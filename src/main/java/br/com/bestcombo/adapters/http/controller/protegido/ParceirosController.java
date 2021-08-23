package br.com.bestcombo.adapters.http.controller.protegido;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.parceiros.dto.buscaparceiroporcodigo.BuscaParceiroPorCodigoRequisicaoDTO;
import br.com.bestcombo.core.parceiros.dto.buscaparceiroporcodigo.BuscaParceiroPorCodigoRespostaDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/parceiros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParceirosController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @GET
    @Path("/{codigo}")
    public Response buscaParceiroPorCodigo(@BeanParam BuscaParceiroPorCodigoRequisicaoDTO requisicao) throws NegocioException {
        BuscaParceiroPorCodigoRespostaDTO resposta = processadorCasoDeUso.processa(requisicao);

        return Response.ok(resposta).build();
    }

}
