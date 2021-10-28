package br.com.bestcombo.core.pedidos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.bestcombo.core.produtos.entity.ProdutoEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pedido_produto")
@IdClass(ProdutoPedidoPK.class)
public class ProdutoPedidoEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_produto")
    private ProdutoEntity produto;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_pedido")
    private PedidoEntity pedido;

    @Column(name = "valor_unitario_produto")
    private BigDecimal valorUnitario;

    @Column(name = "quantidade_produto")
    private Integer quantidade;

    @Column(name = "valor_total_produto")
    private BigDecimal valorTotal;

    @PrePersist
    public void prePesist() {
        valorTotal = calculaValorTotal();
    }

    private BigDecimal calculaValorTotal() {
        return valorUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public BigDecimal getValorTotal() {
        return Optional.ofNullable(valorTotal).orElse(calculaValorTotal());
    }

}
