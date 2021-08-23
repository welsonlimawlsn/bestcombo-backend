package br.com.bestcombo.core.requisicao.service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.ports.service.RequisicaoService;

@Slf4j
@Singleton
public class RequisicaoServiceImpl implements RequisicaoService {

    @Inject
    Validator validator;

    @Override
    public <REQUISICAO extends RequisicaoDTO<RESPOSTA>, RESPOSTA extends RespostaDTO> void validaRequisicao(REQUISICAO requisicaoDTO) throws NegocioException {
        Set<ConstraintViolation<REQUISICAO>> constraintViolations = validator.validate(requisicaoDTO);

        if (!(constraintViolations == null || constraintViolations.isEmpty())) {

            throw new NegocioException(Erro.ATRIBUTO_INVALIDO, getMensagens(constraintViolations));
        }

    }

    private <REQUISICAO extends RequisicaoDTO<RESPOSTA>, RESPOSTA extends RespostaDTO> List<String> getMensagens(Set<ConstraintViolation<REQUISICAO>> constraintViolations) {
        return constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
    }

}
