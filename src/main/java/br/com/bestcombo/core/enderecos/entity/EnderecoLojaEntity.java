package br.com.bestcombo.core.enderecos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_endereco_loja")
public class EnderecoLojaEntity {

    @Id
    @Column(name = "codigo_endereco_loja")
    private UUID codigo;

    @Column(name = "cep_endereco_loja")
    private String cep;

    @Column(name = "rua_endereco_loja")
    private String rua;

    @Column(name = "bairro_endereco_loja")
    private String bairro;

    @Column(name = "cidade_endereco_loja")
    private String cidade;

    @Column(name = "estado_endereco_loja")
    private String estado;

    @Column(name = "numero_endereco_loja")
    private String numero;

    @Column(name = "complemento_endereco_loja")
    private String complemento;

    @PrePersist
    public void prePersist() {
        codigo = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnderecoLojaEntity that = (EnderecoLojaEntity) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return 709897528;
    }

}
