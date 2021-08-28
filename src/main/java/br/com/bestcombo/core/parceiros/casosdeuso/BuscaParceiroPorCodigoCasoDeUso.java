package br.com.bestcombo.core.parceiros.casosdeuso;

import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.parceiros.dto.buscaparceiroporcodigo.BuscaParceiroPorCodigoRequisicaoDTO;
import br.com.bestcombo.core.parceiros.dto.buscaparceiroporcodigo.BuscaParceiroPorCodigoRespostaDTO;
import br.com.bestcombo.ports.dao.PessoaDAO;

@CasoDeUso(CasosDeUso.BUSCA_PARCEIRO_POR_CODIGO)
@ApplicationScoped
public class BuscaParceiroPorCodigoCasoDeUso extends AbstractCasoDeUso<BuscaParceiroPorCodigoRequisicaoDTO, BuscaParceiroPorCodigoRespostaDTO> {

    @Inject
    PessoaDAO parceiroDAO;

    @Inject
    SecurityIdentity securityIdentity;

    @Override
    protected void processa(BuscaParceiroPorCodigoRequisicaoDTO requisicao, BuscaParceiroPorCodigoRespostaDTO resposta) throws NegocioException {
        boolean parceiros = securityIdentity.hasRole("parceiros");

        if (!parceiros) {
            throw new UnauthorizedException();
        }

//        ParceiroEntity parceiroEntity = parceiroDAO.buscaPorId(requisicao.getCodigo())
//                .orElseThrow(() -> new NegocioException(Erro.PARCEIRO_NAO_ENCONTRADO));

//        mapperParaDTO(parceiroEntity, resposta);
    }

}
