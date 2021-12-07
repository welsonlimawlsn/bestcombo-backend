package br.com.bestcombo.core.pedidos.casodeuso;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.AceitaPedidoMailTemplate;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.AlteraSituacaoPedidoRequisicaoDTO;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.AlteraSituacaoPedidoRespostaDTO;
import br.com.bestcombo.core.pedidos.dto.aceitapedido.CancelaPedidoMailTemplate;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.ports.dao.PedidoDAO;
import br.com.bestcombo.ports.service.EmailService;
import br.com.bestcombo.ports.service.PagamentoService;
import br.com.bestcombo.ports.service.SegurancaService;

@ApplicationScoped
@CasoDeUso(CasosDeUso.ALTERA_SITUACAO_PEDIDO)
public class AlteraSituacaoPedidoCasoDeUso extends AbstractCasoDeUso<AlteraSituacaoPedidoRequisicaoDTO, AlteraSituacaoPedidoRespostaDTO> {

    @Inject
    PedidoDAO pedidoDAO;

    @Inject
    SegurancaService segurancaService;

    @Inject
    PagamentoService pagamentoService;

    @Inject
    EmailService emailService;

    @Override
    protected void processa(AlteraSituacaoPedidoRequisicaoDTO requisicao, AlteraSituacaoPedidoRespostaDTO resposta) throws NegocioException {
        PedidoEntity pedidoEntity = pedidoDAO.buscaPorIdECodigoParceiro(requisicao.getCodigoPedido(), segurancaService.getCodigoUsuarioLogado())
                .orElseThrow(() -> new NegocioException(Erro.PEDIDO_NAO_ENCONTRADO));

        switch (requisicao.getRespostaParceiro()) {
            case "PARCEIRO_ACEITA":
                aceitaPedido(pedidoEntity);
                break;
            case "PARCEIRO_RECUSA":
                cancelaPagamento(pedidoEntity, requisicao.getMotivoCancelamento());
                break;
            case "PARCEIRO_CONCLUI":
                pedidoEntity.conclui();
                break;
        }

    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private void cancelaPagamento(PedidoEntity pedidoEntity, String motivoCancelamento) throws NegocioException {
        pedidoEntity.cancela(motivoCancelamento);
        emailService.sendMail(CancelaPedidoMailTemplate.builder()
                        .to(pedidoEntity.getCliente().getEmail())
                        .subject("Pedido cancelado")
                        .nomeUsuario(pedidoEntity.getCliente().getNome())
                        .codigoPedido(pedidoEntity.getCodigo().toString())
                        .motivoCancelamento(motivoCancelamento)
                .build());
    }

    private void aceitaPedido(PedidoEntity pedidoEntity) throws NegocioException {
        try {
            pagamentoService.processaPagamento(pedidoEntity);
            pedidoEntity.aceita();
            emailService.sendMail(AceitaPedidoMailTemplate.builder()
                            .to(pedidoEntity.getCliente().getEmail())
                            .subject("Nosso paceiro já vai preparar o seu pedido")
                            .codigoPedido(pedidoEntity.getCodigo().toString())
                            .nomeCliente(pedidoEntity.getCliente().getNome())
                    .build());
        } catch (InfraestruturaException e) {
            cancelaPagamento(pedidoEntity, "Erro no pagamento, Sr(a). cliente, verificar ou trocar os dados de pagamento");
            throw new NegocioException(Erro.PROBLEMA_NO_PAGAMENTO);
        }
    }

}
