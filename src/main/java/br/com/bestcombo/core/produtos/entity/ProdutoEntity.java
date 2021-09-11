package br.com.bestcombo.core.produtos.entity;

import br.com.bestcombo.core.loja.entity.LojaEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "tb_produto")
public class ProdutoEntity {
    @Id
    @Column(name="codigo_produto")
    private UUID codigo;
    @Column(name="nome_produto")
    private String nome;
    @Column(name="descricao")
    private String descricao;
    @Column(name="preco")
    private BigDecimal preco;
    @ManyToOne
    @JoinColumn(name = "codigo_loja")
    private LojaEntity loja;
    @PrePersist
    public void prePersiste(){
        codigo = UUID.randomUUID();
    }

}
