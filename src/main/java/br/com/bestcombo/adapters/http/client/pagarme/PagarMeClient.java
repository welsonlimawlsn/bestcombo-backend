package br.com.bestcombo.adapters.http.client.pagarme;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.bestcombo.adapters.http.client.pagarme.dto.RequisicaoPagarMe;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao.NovaTransacaoRequisicao;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novatransacao.NovaTransacaoResposta;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novocartao.NovoCartaoRequisicao;
import br.com.bestcombo.adapters.http.client.pagarme.dto.novocartao.NovoCartaoResposta;

@Path("/1")
@RegisterRestClient
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PagarMeClient {

    @Path("/cards")
    @POST
    NovoCartaoResposta novoCartao(NovoCartaoRequisicao requisicao);

    @Path("/transactions")
    @POST
    NovaTransacaoResposta novaTransancao(NovaTransacaoRequisicao requisicao);

    @Path("/transactions/{codigo}")
    @GET
    NovaTransacaoResposta buscaTransacao(@PathParam("codigo") String codigoTransacao, @BeanParam RequisicaoPagarMe requisicao);

}
