package br.com.bestcombo.adapters.http.controller.publico;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.clientes.dto.cadastrocliente.CadastroClienteRequisicaoDTO;
import br.com.bestcombo.core.clientes.dto.cadastrocliente.CadastroClienteRespostaDTO;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/publico/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @POST
    public Response cadastraCliente(CadastroClienteRequisicaoDTO requisicao) throws NegocioException {
        CadastroClienteRespostaDTO resposta = processadorCasoDeUso.processa(requisicao);

        return Response.created(URI.create("/parceiros/" + resposta.getCodigo())).build();
    }

}
