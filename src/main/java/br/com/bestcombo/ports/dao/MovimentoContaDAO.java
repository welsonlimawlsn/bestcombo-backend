package br.com.bestcombo.ports.dao;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

import br.com.bestcombo.core.movimentoconta.entity.MovimentoContaEntity;

public interface MovimentoContaDAO extends DAO<MovimentoContaEntity, UUID> {

    Collection<MovimentoContaEntity> listaMovimentosNaoEfetivadosEAnterioresADataAtual();

    Collection<MovimentoContaEntity> listaMovimentosPorDataIncioFimECodigoParceiro(ZonedDateTime dataInicio, ZonedDateTime dataFim, UUID codigoUsuarioLogado);

}
