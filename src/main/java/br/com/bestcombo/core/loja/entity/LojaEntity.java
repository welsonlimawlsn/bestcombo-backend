package br.com.bestcombo.core.loja.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.bestcombo.core.enderecos.entity.EnderecoLojaEntity;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_loja")
@NamedQueries({
        @NamedQuery(name = "buscaLojaPorParceiro", query = "SELECT l FROM LojaEntity l JOIN l.parceiro p WHERE p.tipo = 1 AND p.codigo = :codigoParceiro")
})
public class LojaEntity {

    @Id
    @Column(name = "codigo_loja")
    private UUID codigo;

    @Column(name = "cnpj_loja")
    private String cnpj;

    @Column(name = "nome_loja")
    private String nome;

    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "codigo_endereco_loja")
    private EnderecoLojaEntity endereco;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_pessoa")
    private PessoaEntity parceiro;

    @PrePersist
    public void prePersist() {
        codigo = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LojaEntity that = (LojaEntity) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return 1697106087;
    }

}
