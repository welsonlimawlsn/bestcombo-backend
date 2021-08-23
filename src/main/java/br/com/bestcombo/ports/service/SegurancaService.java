package br.com.bestcombo.ports.service;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;

public interface SegurancaService {

    void verificaAutorizacao(RequisicaoDTO<?> requisicao);

}
