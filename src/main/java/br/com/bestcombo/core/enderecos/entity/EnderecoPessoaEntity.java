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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.bestcombo.core.pessoas.entity.PessoaEntity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_endereco_pessoa")
public class EnderecoPessoaEntity {

    @Id
    @Column(name = "codigo_endereco_pessoa")
    private UUID codigo;

    @Column(name = "cep_endereco_pessoa")
    private String cep;

    @Column(name = "rua_endereco_pessoa")
    private String rua;

    @Column(name = "bairro_endereco_pessoa")
    private String bairro;

    @Column(name = "cidade_endereco_pessoa")
    private String cidade;

    @Column(name = "estado_endereco_pessoa")
    private String estado;

    @Column(name = "numero_endereco_pessoa")
    private String numero;

    @Column(name = "complemento_endereco_pessoa")
    private String complemento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigo_pessoa", nullable = false)
    private PessoaEntity pessoa;

    @PrePersist
    public void prePersist() {
        codigo = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnderecoPessoaEntity that = (EnderecoPessoaEntity) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return 2000614767;
    }

}
