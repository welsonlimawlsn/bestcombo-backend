package br.com.bestcombo.core.saldo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaldoPK implements Serializable {

    private UUID codigoLoja;

    private LocalDate dataSaldo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SaldoPK saldoPK = (SaldoPK) o;
        return codigoLoja != null && Objects.equals(codigoLoja, saldoPK.codigoLoja);
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
