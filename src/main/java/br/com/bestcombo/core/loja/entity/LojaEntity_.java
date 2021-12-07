package br.com.bestcombo.core.loja.entity;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import br.com.bestcombo.core.categoria.entity.CategoriaEntity;
import br.com.bestcombo.core.enderecos.entity.EnderecoLojaEntity;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;

@StaticMetamodel(LojaEntity.class)
public class LojaEntity_ {

    public static volatile SingularAttribute<LojaEntity, UUID> codigo;

    public static volatile SingularAttribute<LojaEntity, String> cnpj;

    public static volatile SingularAttribute<LojaEntity, String> nome;

    public static volatile SingularAttribute<LojaEntity, String> descricao;

    public static volatile SingularAttribute<LojaEntity, String> imagem;

    public static volatile SingularAttribute<LojaEntity, EnderecoLojaEntity> endereco;

    public static volatile SingularAttribute<LojaEntity, PessoaEntity> parceiro;

    public static volatile SetAttribute<LojaEntity, CategoriaEntity> categorias;

    public static volatile ListAttribute<LojaEntity, ProdutoEntity> produtos;

    public static volatile SingularAttribute<LojaEntity, String> chavePix;

    public static volatile SingularAttribute<LojaEntity, String> telefone;

    public static volatile SingularAttribute<LojaEntity, ZonedDateTime> dataCadastro;

}
