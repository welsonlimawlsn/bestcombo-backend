package br.com.bestcombo.core.pessoas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bestcombo.core.enderecos.entity.EnderecoPessoaEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_pessoa")
@NamedQueries({
        @NamedQuery(
                name = "buscaPorCpfOuEmailOuUsuario",
                query = "SELECT p FROM PessoaEntity p WHERE (p.cpf = :cpf AND p.tipo = :tipoPessoa) OR p.email = :email OR p.usuario = :usuario"
        ),
        @NamedQuery(
                name = "listaParceiros",
                query = "SELECT p FROM PessoaEntity p JOIN FETCH p.enderecos WHERE p.tipo = 1"
        )
})
public class PessoaEntity {

    @Id
    @Column(name = "codigo_pessoa")
    private UUID codigo;

    @Column(name = "nome_pessoa", nullable = false)
    private String nome;

    @Column(name = "sobrenome_pessoa", nullable = false)
    private String sobrenome;

    @Column(name = "email_pessoa", nullable = false, unique = true)
    private String email;

    @Column(name = "usuario_pessoa", nullable = false, unique = true)
    private String usuario;

    @Column(name = "cpf_pessoa", nullable = false, unique = true)
    private String cpf;

    @Column(name = "tipo_pessoa", nullable = false)
    private Integer tipo;

    @Column(name = "telefone_pessoa")
    private String telefone;

    @OneToMany(mappedBy = "pessoa")
    private Set<EnderecoPessoaEntity> enderecos;

    @Transient
    public boolean isParceiro() {
        return tipo == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PessoaEntity that = (PessoaEntity) o;

        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return 551525451;
    }

}
