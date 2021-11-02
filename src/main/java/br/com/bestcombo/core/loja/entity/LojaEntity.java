package br.com.bestcombo.core.loja.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.bestcombo.core.categoria.entity.CategoriaEntity;
import br.com.bestcombo.core.enderecos.entity.EnderecoLojaEntity;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_loja")
@NamedQueries({
        @NamedQuery(name = "buscaLojaPorParceiro", query = "SELECT l FROM LojaEntity l JOIN l.parceiro p WHERE p.tipo = 1 AND p.codigo = :codigoParceiro"),
        @NamedQuery(name = "buscaLojaPorCNPJ", query = "SELECT l FROM LojaEntity l WHERE l.cnpj = :cnpj"),
        @NamedQuery(name = "buscaLojaPorCodigoEParceiro", query = "SELECT l FROM LojaEntity l join l.parceiro as p WHERE l.codigo = :codigo and p.codigo = :codigoParceiro"),
        @NamedQuery(name = "buscaLojaPorTermo", query = "SELECT l FROM LojaEntity l JOIN l.categorias c JOIN l.produtos p JOIN p.categorias cp WHERE lower(l.nome) LIKE :termo OR lower(l.descricao) LIKE :termo OR lower(c.nome) LIKE :termo OR lower(p.nome) LIKE :termo OR lower(p.descricao) LIKE :termo OR lower(cp.nome) LIKE :termo")
})
public class LojaEntity {

    @Id
    @Column(name = "codigo_loja")
    private UUID codigo;

    @Column(name = "cnpj_loja")
    private String cnpj;

    @Column(name = "nome_loja")
    private String nome;

    @Column(name = "descricao_loja")
    private String descricao;

    @Column(name = "imagem_loja")
    private String imagem;

    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "codigo_endereco_loja")
    private EnderecoLojaEntity endereco;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_pessoa")
    private PessoaEntity parceiro;

    @ManyToMany
    @JoinTable(
            name = "tb_loja_categoria",
            joinColumns = @JoinColumn(name = "codigo_loja"),
            inverseJoinColumns = @JoinColumn(name = "codigo_categoria"))
    private Set<CategoriaEntity> categorias;

    @OneToMany(mappedBy = "loja")
    private List<ProdutoEntity> produtos;

    @Column(name = "chave_pix_loja")
    private String chavePix;

    @Column(name = "telefone_loja")
    private String telefone;

    @PrePersist
    public void prePersist() {
        codigo = UUID.randomUUID();
    }

    public void setChavePix(String chavePix) throws NegocioException {
        if (this.chavePix != null) {
            throw new NegocioException(Erro.CHAVE_PIX_NAO_PODE_SER_ALTERADA);
        }
        this.chavePix = chavePix;
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
