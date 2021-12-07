package br.com.bestcombo.adapters.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;

import br.com.bestcombo.core.casosdeuso.CasoDeUsoEntity;
import br.com.bestcombo.core.papel.entity.PapelEntity;
import br.com.bestcombo.ports.dao.PapelDAO;

@ApplicationScoped
public class PapelDAOImpl extends DAOImpl<PapelEntity, Integer> implements PapelDAO {

    public PapelDAOImpl() {
        super(PapelEntity.class);
    }

    @Override
    public List<String> buscaPapeisPorCasoDeUso(CasoDeUsoEntity casoDeUso) {
        TypedQuery<String> query = entityManager.createNamedQuery("buscaPorCasoDeUso", String.class);

        query.setParameter("casoDeUso", casoDeUso);

        return query.getResultList();
    }

    @Override
    public List<String> buscaPapeisPorCodigoCasoDeUso(Integer casoDeUso) {
        TypedQuery<String> query = entityManager.createQuery("SELECT p.nome FROM PapelEntity p join p.casosDeUso cdu where cdu.codigo = :codigo", String.class);
        query.setParameter("codigo", casoDeUso);
        return query.getResultList();
    }

}
