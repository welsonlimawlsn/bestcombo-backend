package br.com.bestcombo.adapters.http.controller.protegido;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.UUID;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.solicitacaosaque.dto.alterasituacao.AlteraSituacaoSolicitacaoSaqueRequisicaoDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.alterasituacao.AlteraSituacaoSolicitacaoSaqueRespostaDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.consultasolicitacaoandamento.ConsultaSolicitacaoSaqueRequisicaoDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.consultasolicitacaoandamento.ConsultaSolicitacaoSaqueRespostaDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.novasolicitacao.NovaSolicitacaoSaqueRequisicaoDTO;
import br.com.bestcombo.core.solicitacaosaque.dto.novasolicitacao.NovaSolicitacaoSaqueRespostaDTO;
import br.com.bestcombo.ports.casodeuso.ProcessadorCasoDeUso;

@RequiredArgsConstructor
@Path("solicitacao-saque")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SolicitacaoSaqueController {

    final ProcessadorCasoDeUso processadorCasoDeUso;

    @POST
    public Response solicitaSaque(NovaSolicitacaoSaqueRequisicaoDTO requisicaoDTO) throws NegocioException {
        NovaSolicitacaoSaqueRespostaDTO respostaDTO = processadorCasoDeUso.processa(requisicaoDTO);

        return Response.created(URI.create("/solicitacao-saque/" + respostaDTO.getCodigo())).build();
    }

    @GET
    @Path("andamento")
    public Response consultaSolicitacaoAndamento(@BeanParam ConsultaSolicitacaoSaqueRequisicaoDTO requisicao) throws NegocioException {
        ConsultaSolicitacaoSaqueRespostaDTO respostaDTO = processadorCasoDeUso.processa(requisicao);

        return Response.ok(respostaDTO).build();
    }

    @PATCH
    @Path("{codigo}")
    public Response alteraSituacaoSolicitacaoSaque(@PathParam("codigo") UUID codigo, AlteraSituacaoSolicitacaoSaqueRequisicaoDTO requisicaoDTO) throws NegocioException {
        requisicaoDTO.setCodigoSolicitacao(codigo);

        AlteraSituacaoSolicitacaoSaqueRespostaDTO respostaDTO = processadorCasoDeUso.processa(requisicaoDTO);

        return Response.ok().build();
    }

}
