package br.com.bestcombo.core.loja.casodeuso;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.loja.dto.LojaDTO;
import br.com.bestcombo.core.loja.dto.listaultimosparceiros.ListaUltimosParceirosCadastradosRequisicaoDTO;
import br.com.bestcombo.core.loja.dto.listaultimosparceiros.ListaUltimosParceirosCadastradosRespostaDTO;
import br.com.bestcombo.core.loja.entity.LojaEntity;
import br.com.bestcombo.core.loja.mapper.LojaMapper;
import br.com.bestcombo.ports.dao.LojaDAO;

@RequiredArgsConstructor
@ApplicationScoped
@CasoDeUso(CasosDeUso.LISTA_ULTIMOS_PARCEIROS)
public class ListaUltimosParceirosCadastradosCasoDeUso extends AbstractCasoDeUso<ListaUltimosParceirosCadastradosRequisicaoDTO, ListaUltimosParceirosCadastradosRespostaDTO> {

    final LojaDAO lojaDAO;

    @Override
    protected void processa(ListaUltimosParceirosCadastradosRequisicaoDTO req, ListaUltimosParceirosCadastradosRespostaDTO res) throws NegocioException {
        Collection<LojaEntity> entities = lojaDAO.listaUltimasLojas();

        List<LojaDTO> dtos = LojaMapper.mapperParaDTO(entities, true);

        res.setLojas(dtos);
    }

}
