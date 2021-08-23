package br.com.bestcombo.adapters.http.mapper;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.bestcombo.adapters.http.mapper.dto.ErroDTO;
import br.com.bestcombo.core.exception.NegocioException;

@Slf4j
@Provider
public class NegocioExceptionMapper implements ExceptionMapper<NegocioException> {

    @Override
    public Response toResponse(NegocioException e) {
        log.warn("Ocorreu um erro enquanto processava uma requisicao. {}", e.getErro());

        Response.Status statusCode = ErroParaStatusCodeMapper.getStatusCodePorErro(e.getErro());

        return Response.status(statusCode).entity(criaErro(e, statusCode)).build();
    }

    private ErroDTO criaErro(NegocioException e, Response.Status statusCode) {
        return ErroDTO.builder()
                .mensagens(e.getMensagem())
                .codigoHttp(statusCode.getStatusCode())
                .descricaoCodigoHttp(statusCode.getReasonPhrase())
                .codigoInterno(e.getErro().getCodigo())
                .build();
    }

}
