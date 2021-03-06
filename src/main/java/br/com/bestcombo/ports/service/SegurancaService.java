package br.com.bestcombo.ports.service;

import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;

import java.util.UUID;

public interface SegurancaService {

    void verificaAutorizacao(RequisicaoDTO<?> requisicao) throws NegocioException;

    void validaPessoaLogada(UUID codigoPessoa);

    boolean isParceiro();

    PessoaEntity getUsuarioLogado();

    UUID getCodigoUsuarioLogado();

    boolean isCliente();

}
