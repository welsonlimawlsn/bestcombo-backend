package br.com.bestcombo.adapters.http.controller.publico;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.categoria.dto.listacategorias.ListaCategoriasRequisicaoDTO;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("publico/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @GET
    public Response listaCategorias(@BeanParam ListaCategoriasRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

}
