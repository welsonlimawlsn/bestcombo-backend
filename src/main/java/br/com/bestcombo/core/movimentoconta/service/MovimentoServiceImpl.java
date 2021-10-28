package br.com.bestcombo.core.movimentoconta.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.movimentoconta.entity.MovimentoContaEntity;
import br.com.bestcombo.core.movimentoconta.enums.TipoMovimentoConta;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedido;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;
import br.com.bestcombo.ports.dao.MovimentoContaDAO;
import br.com.bestcombo.ports.service.MovimentoService;
import br.com.bestcombo.ports.service.SaldoService;

@ApplicationScoped
public class MovimentoServiceImpl implements MovimentoService {

    private static final BigDecimal PERCENTUAL_BESTCOMBO = new BigDecimal("15");

    private static final LojaEntity LOJA_BESTCOMBO = LojaEntity.builder()
            .codigo(UUID.fromString("ea31c776-fadb-4c4d-895f-84d18d25b452"))
            .build();

    public static final String CUSTO_FIXO_PAGARME = "0.99";

    public static final String PERCENTUAL_PAGARME = "4.99";

    @Inject
    MovimentoContaDAO movimentoContaDAO;

    @Inject
    SaldoService saldoService;

    @Transactional(Transactional.TxType.REQUIRED)
    @Override
    public int atualizaMovimentosNaoEfetidos() throws NegocioException {
        Collection<MovimentoContaEntity> movimentos = movimentoContaDAO.listaMovimentosNaoEfetivadosEAnterioresADataAtual();
        for (MovimentoContaEntity movimento : movimentos) {
            saldoService.atualizaSaldo(movimento);
        }
        return movimentos.size();
    }

    @Override
    public void criaMovimentoPedido(PedidoEntity pedido) throws NegocioException {
        if (!pedido.getSituacao().equals(SituacaoPedido.PARCEIRO_PREPARANDO_PEDIDO)) {
            throw new NegocioException(Erro.PEDIDO_INVALIDO_CRIAR_MOVIMENTO);
        }

        BigDecimal valorTotalPedido = pedido.getValorTotal();
        BigDecimal custoTransacao = calculaCustoTransancao(valorTotalPedido);
        BigDecimal valorBestcombo = calculaParteBestcombo(valorTotalPedido);

        ZonedDateTime dataHora = ZonedDateTime.now(ZoneOffset.UTC);

        List<MovimentoContaEntity> movimentos = List.of(
                criaMovimento(pedido, valorTotalPedido, dataHora, pedido.getLoja(), TipoMovimentoConta.CREDITO, "Venda Pedido"),
                criaMovimento(pedido, valorBestcombo, dataHora, pedido.getLoja(), TipoMovimentoConta.DEBITO, "Custo Plataforma"),
                criaMovimento(pedido, valorBestcombo, dataHora, LOJA_BESTCOMBO, TipoMovimentoConta.CREDITO, "Percentual Venda Pedido"),
                criaMovimento(pedido, custoTransacao, dataHora, LOJA_BESTCOMBO, TipoMovimentoConta.DEBITO, "Custo Transanção")
        );

        for (MovimentoContaEntity movimento : movimentos) {
            movimentoContaDAO.persiste(movimento);
            saldoService.atualizaSaldo(movimento);
        }
    }

    @Override
    public void criaMovimentoSaque(SolicitacaoSaqueEntity solicitacaoSaque) throws NegocioException {
        ZonedDateTime dataHora = ZonedDateTime.now(ZoneOffset.UTC);
        MovimentoContaEntity movimentoContaEntity = MovimentoContaEntity.builder()
                .tipoMovimento(TipoMovimentoConta.DEBITO)
                .dataHora(dataHora)
                .dataHoraEfetivacao(dataHora)
                .efetivado(false)
                .loja(solicitacaoSaque.getLoja())
                .descricao("Saque")
                .valor(solicitacaoSaque.getValor())
                .solicitacaoSaque(solicitacaoSaque)
                .build();

        movimentoContaDAO.persiste(movimentoContaEntity);
        saldoService.atualizaSaldo(movimentoContaEntity);
    }

    private MovimentoContaEntity criaMovimento(PedidoEntity pedido, BigDecimal valor, ZonedDateTime dataHora, LojaEntity loja, TipoMovimentoConta tipoMovimento, String descricao) {
        return MovimentoContaEntity.builder()
                .tipoMovimento(tipoMovimento)
                .dataHora(dataHora)
                .pedido(pedido)
                .dataHoraEfetivacao(dataHora.plusDays(30))
                .efetivado(false)
                .loja(loja)
                .valor(valor)
                .descricao(descricao)
                .build();
    }

    private BigDecimal calculaParteBestcombo(BigDecimal valorTotalPedido) {
        return valorTotalPedido
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN)
                .multiply(PERCENTUAL_BESTCOMBO);
    }

    private BigDecimal calculaCustoTransancao(BigDecimal valorTotalPedido) {
        return valorTotalPedido
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_EVEN)
                .multiply(new BigDecimal(PERCENTUAL_PAGARME))
                .add(new BigDecimal(CUSTO_FIXO_PAGARME));
    }

}
