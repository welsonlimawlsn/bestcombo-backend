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
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldoMensalBestcomboRequisicaoDTO;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosBestcomboRequisicaoDTO;
import br.com.bestcombo.core.saldo.dto.consultasaldos.ConsultaSaldosParceiroRequisicaoDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("saldos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SaldoController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @GET
    public Response consultaSaldoParceiro(@BeanParam ConsultaSaldosParceiroRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

    @GET
    @Path("bestcombo")
    public Response consultaSaldoBestcom(@BeanParam ConsultaSaldosBestcomboRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

    @GET
    @Path("mensal")
    public Response consultaSaldosPorMes(@BeanParam ConsultaSaldoMensalBestcomboRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

}
