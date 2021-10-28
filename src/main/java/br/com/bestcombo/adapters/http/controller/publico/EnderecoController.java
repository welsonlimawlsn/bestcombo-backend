package br.com.bestcombo.adapters.http.controller.publico;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.enderecos.dto.buscaendereco.BuscaEnderecoPorCEPRequisicaoDTO;
import br.com.bestcombo.core.enderecos.dto.buscaendereco.BuscaEnderecoPorCEPRespostaDTO;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/publico/endereco")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EnderecoController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @GET
    @Path("/{cep}")
    public Response buscaEnderecoPorCEP(@BeanParam BuscaEnderecoPorCEPRequisicaoDTO requisicao) throws NegocioException {
        BuscaEnderecoPorCEPRespostaDTO resposta = processadorCasoDeUso.processa(requisicao);

        return Response.ok(resposta).build();
    }

}
