package br.com.bestcombo.adapters.dao;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.movimentoconta.entity.MovimentoContaEntity;
import br.com.bestcombo.ports.dao.MovimentoContaDAO;

@ApplicationScoped
public class MovimentoContaDAOImpl extends DAOImpl<MovimentoContaEntity, UUID> implements MovimentoContaDAO {

    public MovimentoContaDAOImpl() {
        super(MovimentoContaEntity.class);
    }

    @Override
    public Collection<MovimentoContaEntity> listaMovimentosNaoEfetivadosEAnterioresADataAtual() {

        TypedQuery<MovimentoContaEntity> query = entityManager.createQuery("SELECT m FROM MovimentoContaEntity m WHERE m.efetivado = false AND m.dataHoraEfetivacao < :dataAtual", MovimentoContaEntity.class);

        query.setParameter("dataAtual", ZonedDateTime.now(ZoneOffset.UTC));

        return query.getResultList();
    }

    @Override
    public Collection<MovimentoContaEntity> listaMovimentosPorDataIncioFimECodigoParceiro(ZonedDateTime dataInicio, ZonedDateTime dataFim, UUID codigoUsuarioLogado) {
        String sql = "SELECT m FROM MovimentoContaEntity m WHERE m.loja.parceiro.codigo =: codigo AND m.dataHora >= :dataIncio";

        if (dataFim != null) {
            sql = sql + " AND m.dataHora < :dataFim";
        }

        sql = sql + " order by m.dataHora desc, m.tipoMovimento desc ";

        TypedQuery<MovimentoContaEntity> query = entityManager.createQuery(sql, MovimentoContaEntity.class);

        query.setParameter("codigo", codigoUsuarioLogado);
        query.setParameter("dataIncio", dataInicio);

        if (dataFim != null) {
            query.setParameter("dataFim", dataFim);
        }

        return query.getResultList();
    }

}
