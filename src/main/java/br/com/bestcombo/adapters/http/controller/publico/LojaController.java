package br.com.bestcombo.adapters.http.controller.publico;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.dto.buscalojaportermo.BuscaLojaPorTermoRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.listalojas.ListaLojasRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.listalojas.ListaLojasRespostaDTO;
import br.com.bestcombo.core.loja.dto.listaultimosparceiros.ListaUltimosParceirosCadastradosRequisicaoDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/publico/lojas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LojaController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @GET
    public Response listaLojas(@BeanParam ListaLojasRequisicaoDTO requisicaoDTO) throws NegocioException {
        ListaLojasRespostaDTO respostaDTO = processadorCasoDeUso.processa(requisicaoDTO);

        return Response.ok(respostaDTO).build();
    }

    @GET
    @Path("/busca")
    public Response buscaLojasPorTermo(@BeanParam BuscaLojaPorTermoRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

    @GET
    @Path("/ultimas")
    public Response listaUltimosParceiros(@BeanParam ListaUltimosParceirosCadastradosRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

}
