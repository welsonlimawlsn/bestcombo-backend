package br.com.bestcombo.ports.service;

import br.com.bestcombo.core.enderecos.modelos.Endereco;
import br.com.bestcombo.core.exception.NegocioException;

public interface EnderecoService {

    Endereco buscaEnderecoPorCep(String cep) throws NegocioException;

}
