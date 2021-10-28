package br.com.bestcombo.ports.dao;

import java.util.Optional;
import java.util.UUID;

import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.solicitacaosaque.entity.SolicitacaoSaqueEntity;

public interface SolicitacaoSaqueDAO extends DAO<SolicitacaoSaqueEntity, UUID> {

    Optional<SolicitacaoSaqueEntity> buscaSolicitacaoPendentePorLoja(LojaEntity lojaEntity);

}
