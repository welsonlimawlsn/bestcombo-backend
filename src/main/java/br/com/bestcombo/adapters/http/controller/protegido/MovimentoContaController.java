package br.com.bestcombo.adapters.http.controller.protegido;

import lombok.RequiredArgsConstructor;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimBestcomboRequisicaoDTO;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimParceiroRequisicaoDTO;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimRequisicaoDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@RequiredArgsConstructor
@Path("movimentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimentoContaController {

    final ProcessadorCasoDeUso processadorCasoDeUso;

    @GET
    public Response listaMovimentosPorDataInicioFimParceiro(@BeanParam ListaMovimentosPorDataInicioFimParceiroRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

    @GET
    @Path("bestcombo")
    public Response listaMovimentosPorDataInicioFimBestcombo(@BeanParam ListaMovimentosPorDataInicioFimBestcomboRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

}
