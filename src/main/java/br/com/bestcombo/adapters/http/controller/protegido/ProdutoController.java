package br.com.bestcombo.adapters.http.controller.protegido;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.produtos.dto.excluiproduto.ExcluiProdutoRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.excluiproduto.ExcluiProdutoRespostaDTO;
import br.com.bestcombo.core.produtos.dto.novoproduto.NovoProdutoRequisicaoDTO;
import br.com.bestcombo.core.produtos.dto.novoproduto.NovoProdutoRespostaDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @Inject
    ProcessadorCasoDeUso processadorCasoDeUso;

    @POST
    public Response novoProduto(NovoProdutoRequisicaoDTO requisicaoDTO) throws NegocioException {
        NovoProdutoRespostaDTO respostaDTO = processadorCasoDeUso.processa(requisicaoDTO);
        return Response.ok(respostaDTO).build();
    }

    @DELETE
    @Path("{codigo}")
    public Response excluiProduto(@BeanParam ExcluiProdutoRequisicaoDTO requisicaoDTO) throws NegocioException {
        ExcluiProdutoRespostaDTO resposta = processadorCasoDeUso.processa(requisicaoDTO);

        return Response.ok().build();
    }

}
