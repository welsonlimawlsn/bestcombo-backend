package br.com.bestcombo.adapters.http.controller.protegido;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.dto.cadastroloja.CadastroLojaRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.cadastroloja.CadastroLojaRespostaDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/loja")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LojaController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @POST
    public Response cadastraLoja(CadastroLojaRequisicaoDTO requisicao) throws NegocioException {
        CadastroLojaRespostaDTO resposta = processadorCasoDeUso.processa(requisicao);

        return Response.created(URI.create("/loja/" + resposta.getCodigo())).build();
    }

}
