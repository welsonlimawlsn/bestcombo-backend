package br.com.bestcombo.core.produtos.entity;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.bestcombo.core.categoria.entity.CategoriaEntity;
import br.com.bestcombo.core.loja.entity.LojaEntity;

@StaticMetamodel(ProdutoEntity.class)
public class ProdutoEntity_ {

    public static volatile SingularAttribute<ProdutoEntity, UUID> codigo;

    public static volatile SingularAttribute<ProdutoEntity, String> nome;

    public static volatile SingularAttribute<ProdutoEntity, String> descricao;

    public static volatile SingularAttribute<ProdutoEntity, BigDecimal> preco;

    public static volatile SingularAttribute<ProdutoEntity, Integer> quantidadePessoas;

    public static volatile SingularAttribute<ProdutoEntity, String> imagem;

    public static volatile SetAttribute<ProdutoEntity, CategoriaEntity> categorias;

    public static volatile SingularAttribute<ProdutoEntity, LojaEntity> loja;

    public static volatile SingularAttribute<ProdutoEntity, Boolean> ativo;

}
