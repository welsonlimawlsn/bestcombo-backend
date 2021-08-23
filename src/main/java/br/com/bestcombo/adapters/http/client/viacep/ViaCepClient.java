package br.com.bestcombo.adapters.http.client.viacep;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import br.com.bestcombo.adapters.http.client.viacep.dto.EnderecoResponseDTO;

@Path("/ws")
@RegisterRestClient
public interface ViaCepClient {

    @GET
    @Path("/{cep}/json")
    EnderecoResponseDTO buscaEnderecoPorCep(@PathParam String cep);

}
