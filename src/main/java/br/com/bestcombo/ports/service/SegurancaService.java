package br.com.bestcombo.ports.service;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;

public interface SegurancaService {

    void verificaAutorizacao(RequisicaoDTO<?> requisicao);

    void validaPessoaLogada(String codigoPessoa);

    boolean isParceiro();

    PessoaEntity getUsuarioLogado();

}
