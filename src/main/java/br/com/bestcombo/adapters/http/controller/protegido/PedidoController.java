package br.com.bestcombo.adapters.http.controller.protegido;

import java.net.URI;
import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.AlteraSituacaoPedidoRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.AlteraSituacaoPedidoRespostaDTO;
import br.com.bestcombo.core.pedidos.dto.listapedidos.ListaPedidosRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.listapedidos.ListaPedidosRespostaDTO;
import br.com.bestcombo.core.pedidos.dto.novopedido.NovoPedidoRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.novopedido.NovoPedidoRespostaDTO;
import br.com.bestcombo.core.pedidos.dto.pedidoporcodigo.BuscaPedidoPorCodigoRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.pedidoporcodigo.BuscaPedidoPorCodigoRespostaDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @POST
    public Response novoPedido(NovoPedidoRequisicaoDTO requisicao) throws NegocioException {
        NovoPedidoRespostaDTO resposta = processadorCasoDeUso.processa(requisicao);

        return Response.created(URI.create("/pedidos/" + resposta.getCodigoPedido())).build();
    }

    @PUT
    @Path("{codigoPedido}")
    public Response alteraSituacaoPedido(@PathParam("codigoPedido") UUID codigo, AlteraSituacaoPedidoRequisicaoDTO requisicao) throws NegocioException {
        requisicao.setCodigoPedido(codigo);

        AlteraSituacaoPedidoRespostaDTO resposta = processadorCasoDeUso.processa(requisicao);

        return Response.ok().build();
    }

    @GET
    public Response listaPedidos(@BeanParam ListaPedidosRequisicaoDTO requisicao) throws NegocioException {
        ListaPedidosRespostaDTO resposta = processadorCasoDeUso.processa(requisicao);

        return Response.ok(resposta).build();
    }

    @GET
    @Path("{codigo}")
    public Response buscaPedidoPorCodigo(@BeanParam BuscaPedidoPorCodigoRequisicaoDTO requisicao) throws NegocioException {
        BuscaPedidoPorCodigoRespostaDTO resposta = processadorCasoDeUso.processa(requisicao);

        return Response.ok(resposta).build();
    }

}
