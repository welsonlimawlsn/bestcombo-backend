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
import br.com.bestcombo.core.produtos.dto.buscaproduto.BuscaProdutoPorCodigoRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.buscaproduto.BuscaProdutosPorTermoRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.listaprodutos.ListaProdutosRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.listaprodutos.ListaProdutosRespostaDTO;
import br.com.bestcombo.core.produtos.dto.listaprodutos.ListaUltimosProdutosCadastradosRequisicaoDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/publico/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @GET
    public Response listaProdutos(@BeanParam ListaProdutosRequisicaoDTO requisicaoDTO) throws NegocioException {
        ListaProdutosRespostaDTO resposta = processadorCasoDeUso.processa(requisicaoDTO);

        return Response.ok(resposta).build();
    }

    @GET
    @Path("/{codigo}")
    public Response buscaProdutoPorCodigo(@BeanParam BuscaProdutoPorCodigoRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

    @GET
    @Path("/busca")
    public Response buscaProdutosPorTermo(@BeanParam BuscaProdutosPorTermoRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

    @GET
    @Path("/ultimos-cadastrados")
    public Response listaUltimosProdutosCadastrados(@BeanParam ListaUltimosProdutosCadastradosRequisicaoDTO requisicaoDTO) throws NegocioException {
        return Response.ok(processadorCasoDeUso.processa(requisicaoDTO)).build();
    }

}
