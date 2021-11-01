package br.com.bestcombo.core.produtos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.bestcombo.core.categoria.entity.CategoriaEntity;
import br.com.bestcombo.core.loja.entity.LojaEntity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_produto")
public class ProdutoEntity {

    @Id
    @Column(name = "codigo_produto")
    private UUID codigo;

    @Column(name = "nome_produto")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "quantidade_pessoas")
    private Integer quantidadePessoas;

    @Column(name = "imagem")
    private String imagem;

    @ManyToMany
    @JoinTable(
            name = "tb_produto_categoria",
            joinColumns = @JoinColumn(name = "codigo_produto"),
            inverseJoinColumns = @JoinColumn(name = "codigo_categoria")
    )
    private Set<CategoriaEntity> categorias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_loja")
    private LojaEntity loja;

    @Column(name = "ativo")
    private Boolean ativo;

    @PrePersist
    public void prePersiste() {
        codigo = UUID.randomUUID();
        ativo = true;
    }

    public void desativa() {
        ativo = false;
    }

}
