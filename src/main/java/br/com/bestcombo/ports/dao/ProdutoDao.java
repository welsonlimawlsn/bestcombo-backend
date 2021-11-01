package br.com.bestcombo.ports.dao;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.produtos.entity.ProdutoEntity;

public interface ProdutoDao extends DAO<ProdutoEntity, UUID> {

    Collection<ProdutoEntity> buscaPorIdsECodigoLoja(Collection<UUID> codigoProdutos, LojaEntity codigoEstabelecimento);

    Optional<ProdutoEntity> buscaPorIdEParceiro(UUID codigo, UUID codigoUsuarioLogado);

}
