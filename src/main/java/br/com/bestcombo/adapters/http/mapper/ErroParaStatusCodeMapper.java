package br.com.bestcombo.adapters.http.mapper;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.InfraestruturaException;

public class ErroParaStatusCodeMapper {

    private static final Map<Erro, Response.Status> STATUS_CODES;

    static {
        STATUS_CODES = new HashMap<>();

        STATUS_CODES.put(Erro.CEP_INVALIDO, Response.Status.BAD_REQUEST);
        STATUS_CODES.put(Erro.ATRIBUTO_INVALIDO, Response.Status.BAD_REQUEST);
        STATUS_CODES.put(Erro.PARCEIRO_NAO_ENCONTRADO, Response.Status.NOT_FOUND);
        STATUS_CODES.put(Erro.PESSOA_JA_CADASTRADA, Response.Status.BAD_REQUEST);
        STATUS_CODES.put(Erro.LOJA_NAO_ENCONTRADA, Response.Status.NOT_FOUND);
        STATUS_CODES.put(Erro.LOJA_JA_CADASTRADA, Response.Status.BAD_REQUEST);
        STATUS_CODES.put(Erro.LOJA_INVALIDA, Response.Status.BAD_REQUEST);
        STATUS_CODES.put(Erro.PEDIDO_NAO_PODE_SER_ACEITO, Response.Status.BAD_REQUEST);
        STATUS_CODES.put(Erro.SITUACAO_INVALIDA, Response.Status.BAD_REQUEST);
        STATUS_CODES.put(Erro.JA_POSSUI_UMA_SOLICITACAO_EM_ANDAMENTO, Response.Status.BAD_REQUEST);
        STATUS_CODES.put(Erro.ARQUIVO_NAO_ENCONTRADO, Response.Status.NOT_FOUND);
        STATUS_CODES.put(Erro.PEDIDO_NAO_ENCONTRADO, Response.Status.NOT_FOUND);
        STATUS_CODES.put(Erro.NENHUMA_SOLICITACAO_EM_ANDAMENTO, Response.Status.NOT_FOUND);
    }

    public static Response.Status getStatusCodePorErro(Erro erro) {
        Response.Status status = STATUS_CODES.get(erro);

        if (status == null) {
            throw new InfraestruturaException("Não foi possivel encontrar o status code para o " + erro);
        }

        return status;
    }

}
