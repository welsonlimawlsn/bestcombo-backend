package br.com.bestcombo.ports.service;

import java.util.UUID;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.movimentoconta.entity.MovimentoContaEntity;
import br.com.bestcombo.core.saldo.entity.SaldoEntity;

public interface SaldoService {

    void atualizaSaldo(MovimentoContaEntity movimentoConta) throws NegocioException;

    SaldoEntity getSaldoDia(UUID codigoLoja);

}
