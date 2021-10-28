package br.com.bestcombo.core.movimentoconta.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bestcombo.core.movimentoconta.enums.TipoMovimentoConta;
import br.com.bestcombo.core.movimentoconta.enums.TipoMovimentoContaEnumConverter;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.pedidos.entity.PedidoEntity;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_movimento_conta")
public class MovimentoContaEntity {

    @Id
    @Column(name = "codigo_movimento_conta")
    private UUID codigo;

    @ManyToOne
    @JoinColumn(name = "codigo_loja")
    private LojaEntity loja;

    @OneToOne
    @JoinColumn(name = "codigo_solicitacao_saque")
    private SolicitacaoSaqueEntity solicitacaoSaque;

    @OneToOne
    @JoinColumn(name = "codigo_pedido")
    private PedidoEntity pedido;

    @Column(name = "tipo_movimento_conta")
    @Convert(converter = TipoMovimentoContaEnumConverter.class)
    private TipoMovimentoConta tipoMovimento;

    @Column(name = "valor_movimento_conta")
    private BigDecimal valor;

    @Column(name = "data_movimento_conta")
    private ZonedDateTime dataHora;

    @Column(name = "data_efetivacao_movimento_conta")
    private ZonedDateTime dataHoraEfetivacao;

    @Column(name = "efetivado_movimento_conta")
    private Boolean efetivado;

    @Column(name = "descricao_movimento_conta")
    private String descricao;

    @PrePersist
    public void prePersist() {
        codigo = UUID.randomUUID();
    }

    @Transient
    public boolean isDebito() {
        return TipoMovimentoConta.DEBITO.equals(tipoMovimento);
    }

    @Transient
    public boolean isCredito() {
        return TipoMovimentoConta.CREDITO.equals(tipoMovimento);
    }

    public void efetiva() {
        efetivado = true;
    }

}
