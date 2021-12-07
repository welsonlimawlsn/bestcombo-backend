package br.com.bestcombo.core.pedidos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedido;
import br.com.bestcombo.core.pedidos.enums.SituacaoPedidoEnumConverter;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pedido")
public class PedidoEntity implements Serializable {

    @Id
    @Column(name = "codigo_pedido")
    private UUID codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "codigo_cliente_pedido"
    )
    private PessoaEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "codigo_loja_pedido"
    )
    private LojaEntity loja;

    @Column(name = "data_pedido")
    private ZonedDateTime dataHoraCadastro;

    @Column(name = "data_ultima_atualizacao_pedido")
    private ZonedDateTime dataHoraUltimaAtualizacao;

    @Column(name = "data_agendamento_pedido")
    private ZonedDateTime dataHoraAgendamento;

    @Column(name = "codigo_situacao_pedido")
    @Convert(converter = SituacaoPedidoEnumConverter.class)
    private SituacaoPedido situacao;

    @Column(name = "valor_total_pedido")
    private BigDecimal valorTotal;

    @Column(name = "observacao_pedido")
    private String observacao;

    @Column(name = "motivo_cancelamento_pedido")
    private String motivoCancelamento;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private Set<ProdutoPedidoEntity> produtos;

    @PrePersist
    public void prePersist() {
        codigo = UUID.randomUUID();
        dataHoraCadastro = ZonedDateTime.now(ZoneOffset.UTC);
        dataHoraUltimaAtualizacao = ZonedDateTime.now(ZoneOffset.UTC);
        valorTotal = calculaValorTotal();
        situacao = SituacaoPedido.PEDIDO_AGUARDANDO_APROVACAO_PARCEIRO;
    }

    @PreUpdate
    public void preUpdate() {
        dataHoraUltimaAtualizacao = ZonedDateTime.now(ZoneOffset.UTC);
    }

    private BigDecimal calculaValorTotal() {
        return produtos.stream()
                .map(ProdutoPedidoEntity::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void cancela(String motivoCancelamento) throws NegocioException {
        if (motivoCancelamento == null || motivoCancelamento.isEmpty()) {
            throw new NegocioException(Erro.MOTIVO_RECUSA_OBRIGATORIO);
        }

        validaSePodeCancelarOuAceitar();

        this.motivoCancelamento = motivoCancelamento;
        this.situacao = SituacaoPedido.PEDIDO_CANCELADO;
    }

    private void validaSePodeCancelarOuAceitar() throws NegocioException {
        if (!situacao.equals(SituacaoPedido.PEDIDO_AGUARDANDO_APROVACAO_PARCEIRO)) {
            throw new NegocioException(Erro.SITUACAO_INVALIDA);
        }
    }

    public void aceita() throws NegocioException {
        validaSePodeCancelarOuAceitar();
        situacao = SituacaoPedido.PEDIDO_AGUARDANDO_PAGAMENTO_CLIENTE;

    }

    public void conclui() throws NegocioException {
        validaSePodeConcluir();
        situacao = SituacaoPedido.PEDIDO_CONCLUIDO;
    }

    private void validaSePodeConcluir() throws NegocioException {
        if (!situacao.equals(SituacaoPedido.PARCEIRO_PREPARANDO_PEDIDO)) {
            throw new NegocioException(Erro.SITUACAO_INVALIDA);
        }
    }

}
