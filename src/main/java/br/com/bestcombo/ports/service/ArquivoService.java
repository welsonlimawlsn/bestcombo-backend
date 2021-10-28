package br.com.bestcombo.ports.service;

import java.util.Map;

import br.com.bestcombo.core.exception.NegocioException;

public interface ArquivoService {

    String NOME_ARQUIVO = "NOME_ARQUIVO";
    String TIPO_ARQUIVO = "TIPO_ARQUIVO";
    String ARQUIVO = "ARQUIVO";

    void upload(byte[] arquivo, String tipo, String nome);

    Map<String, Object> download(String nomeArquivo) throws NegocioException;

}
