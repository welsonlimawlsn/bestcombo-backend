package br.com.bestcombo.core.parceiros.casosdeuso;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.parceiros.dto.listaparceiros.ListaParceirosRequisicaoDTO;
import br.com.bestcombo.core.parceiros.dto.listaparceiros.ListaParceirosRespostaDTO;
import br.com.bestcombo.core.pessoas.casodeuso.ListaPessoasCasoDeUso;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.ports.dao.PessoaDAO;

@ApplicationScoped
@CasoDeUso(CasosDeUso.LISTA_PARCEIROS)
public class ListaParceirosCasoDeUso extends ListaPessoasCasoDeUso<ListaParceirosRequisicaoDTO, ListaParceirosRespostaDTO> {

    @Inject
    PessoaDAO pessoaDAO;

    @Override
    protected Set<PessoaEntity> getPessoas() {
        return pessoaDAO.listaParceiros();
    }

}
