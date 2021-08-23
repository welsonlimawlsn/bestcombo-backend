package br.com.bestcombo.ports.casodeuso;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.casosdeuso.dto.RespostaDTO;
import br.com.bestcombo.core.exception.NegocioException;

public interface ProcessadorCasoDeUso {

    <REQUISICAO extends RequisicaoDTO<RESPOSTA>, RESPOSTA extends RespostaDTO> RESPOSTA processa(REQUISICAO requisicao) throws NegocioException;

}
