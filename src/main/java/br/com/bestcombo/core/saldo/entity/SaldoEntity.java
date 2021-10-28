package br.com.bestcombo.core.saldo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_saldo")
@IdClass(SaldoPK.class)
public class SaldoEntity {

    @Id
    @Column(name = "codigo_loja")
    private UUID codigoLoja;

    @Id
    @Column(name = "data_saldo")
    private LocalDate dataSaldo;

    @Column(name = "valor_disponivel")
    private BigDecimal valorDisponivel;

    @Column(name = "valor_futuro")
    private BigDecimal valorFuturo;

    public void debitaSaldoDisponivel(BigDecimal valor) {
        valorDisponivel = valorDisponivel.subtract(valor);
    }

    public void creditaSaldoDisponivel(BigDecimal valor) {
        valorDisponivel = valorDisponivel.add(valor);
    }

    public void debitaSaldoFuturo(BigDecimal valor) {
        valorFuturo = valorFuturo.subtract(valor);
    }

    public void creditaSaldoFuturo(BigDecimal valor) {
        valorFuturo = valorFuturo.add(valor);
    }

    public SaldoEntity copiaComDataAtual() {
        return SaldoEntity.builder()
                .dataSaldo(LocalDate.now())
                .codigoLoja(codigoLoja)
                .valorDisponivel(valorDisponivel)
                .valorFuturo(valorFuturo)
                .build();
    }

}
