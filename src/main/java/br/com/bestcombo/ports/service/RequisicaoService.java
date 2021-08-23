package br.com.bestcombo.ports.service;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.exception.NegocioException;

public interface RequisicaoService {

    <REQUISICAO extends RequisicaoDTO<RESPOSTA>, RESPOSTA extends RespostaDTO> void validaRequisicao(REQUISICAO requisicaoDTO) throws NegocioException;

}
