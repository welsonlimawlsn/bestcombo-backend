package br.com.bestcombo.ports.service;

import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;

public interface MovimentoService {

    int atualizaMovimentosNaoEfetidos() throws NegocioException;

    void criaMovimentoPedido(PedidoEntity pedido) throws NegocioException;

    void criaMovimentoSaque(SolicitacaoSaqueEntity solicitacaoSaque) throws NegocioException;

}
