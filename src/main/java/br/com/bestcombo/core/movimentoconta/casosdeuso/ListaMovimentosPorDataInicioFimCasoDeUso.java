package br.com.bestcombo.core.movimentoconta.casosdeuso;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.movimentoconta.dto.MovimentoContaDTO;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimRequisicaoDTO;
import br.com.bestcombo.core.movimentoconta.dto.listamovimentos.ListaMovimentosPorDataInicioFimRespostaDTO;
import br.com.bestcombo.core.movimentoconta.entity.MovimentoContaEntity;
import br.com.bestcombo.core.movimentoconta.mapper.MovimentoContaMapper;
import br.com.bestcombo.ports.dao.MovimentoContaDAO;

public abstract class ListaMovimentosPorDataInicioFimCasoDeUso<REQ extends ListaMovimentosPorDataInicioFimRequisicaoDTO<RES>, RES extends ListaMovimentosPorDataInicioFimRespostaDTO> extends AbstractCasoDeUso<REQ, RES> {

    @Inject
    MovimentoContaDAO movimentoContaDAO;

    @Override
    protected void processa(REQ req, RES res) throws NegocioException {
        Collection<MovimentoContaEntity> movimentos = movimentoContaDAO.listaMovimentosPorDataIncioFimECodigoParceiro(req.getDataInicio(), req.getDataFim(), getCodigoPessoa());

        List<MovimentoContaDTO> dtos = movimentos.stream()
                .map(MovimentoContaMapper::mapperParaDTO)
                .collect(Collectors.toList());

        res.setMovimentos(dtos);
    }

    protected abstract UUID getCodigoPessoa();

}
