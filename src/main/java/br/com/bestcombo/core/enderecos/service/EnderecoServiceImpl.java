package br.com.bestcombo.core.enderecos.service;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.bestcombo.adapters.http.client.viacep.ViaCepClient;
import br.com.bestcombo.adapters.http.client.viacep.dto.EnderecoResponseDTO;
import br.com.bestcombo.core.enderecos.modelos.Endereco;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.service.EnderecoService;
import br.com.bestcombo.util.EnderecoUtil;
import br.com.bestcombo.util.StringUtil;

@Singleton
@Slf4j
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    @RestClient
    ViaCepClient viaCepClient;

    @Inject
    Validator validator;

    @Override
    public Endereco buscaEnderecoPorCep(String cep) throws NegocioException {
        validaCep(cep);

        log.info("Buscando o CEP {}", cep);

        EnderecoResponseDTO response = viaCepClient.buscaEnderecoPorCep(cep.replace("-", ""));

        validaResposta(response, cep);

        Endereco endereco = mapperParaEndereco(response);

        log.info("CEP {} encontrado", cep);

        return endereco;
    }

    private void validaResposta(EnderecoResponseDTO response, String cep) throws NegocioException {
        Set<ConstraintViolation<EnderecoResponseDTO>> constraintViolations = validator.validate(response);

        if (!constraintViolations.isEmpty()) {
            throwCepInvalido(cep);
        }
    }

    private void validaCep(String cep) throws NegocioException {
        if (StringUtil.ehVazio(cep)) {
            throw new NegocioException(Erro.CEP_INVALIDO, "O CEP não deve ser vázio.");
        }

        if (!EnderecoUtil.cepEhValido(cep)) {
            throwCepInvalido(cep);
        }
    }

    private void throwCepInvalido(String cep) throws NegocioException {
        throw new NegocioException(Erro.CEP_INVALIDO, String.format("O CEP %s é inválido.", cep));
    }

    private Endereco mapperParaEndereco(EnderecoResponseDTO response) {
        return Endereco.builder()
                .cep(response.getCep())
                .endereco(response.getLogradouro())
                .estado(response.getUf())
                .cidade(response.getLocalidade())
                .bairro(response.getBairro())
                .build();
    }

}
