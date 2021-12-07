package br.com.bestcombo.adapters.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.saldo.entity.SaldoEntity;
import br.com.bestcombo.core.saldo.entity.SaldoPK;
import br.com.bestcombo.ports.dao.SaldoDAO;

@ApplicationScoped
public class SaldoDAOImpl extends DAOImpl<SaldoEntity, SaldoPK> implements SaldoDAO {

    public SaldoDAOImpl() {
        super(SaldoEntity.class);
    }

    @Override
    public Optional<SaldoEntity> buscaUltimoSaldoPorLoja(UUID codigoLoja) {

        TypedQuery<SaldoEntity> query = entityManager.createQuery("SELECT s FROM SaldoEntity s WHERE s.codigoLoja = :codigoLoja ORDER BY s.dataSaldo DESC", SaldoEntity.class);

        query.setMaxResults(1);

        query.setParameter("codigoLoja", codigoLoja);

        return getResultadoUnico(query);
    }

    @Override
    public List<SaldoEntity> buscaUltimoSaldoEntreDatas(int ano, Month mes, UUID codigoLoja) {
        LocalDate inicioAtual = LocalDate.of(ano, mes, 1);
        LocalDate fimAtual = inicioAtual.plusMonths(1).minusDays(1);

        LocalDate fimAnterior = inicioAtual.minusDays(1);
        LocalDate inicioAnterior = fimAnterior.minusMonths(1).plusDays(1);

        var sql = "select s.* from tb_saldo s inner join " +
                "(select codigo_loja, max(data_saldo) ultima_data " +
                "from tb_saldo " +
                "where codigo_loja = :codigoLoja " +
                "and data_saldo >= :mesAtualInicio " +
                "and data_saldo <= :mesAtualFim " +
                "group by codigo_loja " +
                "union " +
                "select codigo_loja, max(data_saldo) ultima_data " +
                "from tb_saldo " +
                "where codigo_loja = :codigoLoja " +
                "and data_saldo >= :mesAnteriorInicio " +
                "and data_saldo <= :mesAnteriorFim " +
                "group by codigo_loja) usm " +
                "on usm.codigo_loja = s.codigo_loja and usm.ultima_data = s.data_saldo";

        Query query = entityManager.createNativeQuery(sql, SaldoEntity.class);

        query.setParameter("codigoLoja", codigoLoja);
        query.setParameter("mesAtualInicio", inicioAtual);
        query.setParameter("mesAtualFim", fimAtual);
        query.setParameter("mesAnteriorInicio", inicioAnterior);
        query.setParameter("mesAnteriorFim", fimAnterior);

        return (List<SaldoEntity>) query.getResultList();
    }

    @Override
    public Map<String, BigDecimal> getCreditosDebitosPorMes(Month mes, Integer ano, UUID codigoLoja) {
        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.plusMonths(1).minusDays(1);

        Query nativeQuery = entityManager.createNativeQuery("select sum(valor_movimento_conta) AS valor, " +
                "       'CREDITO' as tipo " +
                "from tb_movimento_conta " +
                "where codigo_loja = :codigoLoja " +
                "  and data_movimento_conta >= :dataInicio " +
                "  and data_movimento_conta <= :dataFim " +
                "and tipo_movimento_conta = 1 " +
                "UNION " +
                "select sum(valor_movimento_conta) as valor, " +
                "'DEBITO' as tipo " +
                "from tb_movimento_conta " +
                "where codigo_loja = :codigoLoja " +
                "  and data_movimento_conta >= :dataInicio " +
                "  and data_movimento_conta <= :dataFim " +
                "and tipo_movimento_conta = 2");

        nativeQuery.setParameter("codigoLoja", codigoLoja);
        nativeQuery.setParameter("dataInicio", inicio);
        nativeQuery.setParameter("dataFim", fim);

        List<Object[]> resultList = nativeQuery.getResultList();

        return resultList.stream()
                .collect(Collectors.toMap(o -> (String) o[1], o -> (BigDecimal) Optional.ofNullable(o[0]).orElse(BigDecimal.ZERO)));
    }

}
