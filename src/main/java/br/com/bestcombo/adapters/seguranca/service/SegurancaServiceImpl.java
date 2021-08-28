package br.com.bestcombo.adapters.seguranca.service;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.CasoDeUsoEntity;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.ports.dao.CasoDeUsoDAO;
import br.com.bestcombo.ports.dao.PapelDAO;
import br.com.bestcombo.ports.dao.PessoaDAO;
import br.com.bestcombo.ports.service.SegurancaService;
import br.com.bestcombo.util.AnotacaoUtil;

@ApplicationScoped
public class SegurancaServiceImpl implements SegurancaService {

    @Inject
    CasoDeUsoDAO casoDeUsoDAO;

    @Inject
    PapelDAO papelDAO;

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    PessoaDAO pessoaDAO;

    @Override
    public void verificaAutorizacao(RequisicaoDTO<?> requisicao) {
        CasoDeUso casoDeUso = AnotacaoUtil.getAnotacao(requisicao, CasoDeUso.class);

        CasoDeUsoEntity casoDeUsoEntity = casoDeUsoDAO.buscaPorId(casoDeUso.value().getCodigo())
                .orElseThrow(() -> new InfraestruturaException(String.format("O caso de uso %s não está presente no banco de dados.", casoDeUso.value())));

        List<String> papeis = papelDAO.buscaPapeisPorCasoDeUso(casoDeUsoEntity);

        if (!papeis.isEmpty()) {
            for (String papel : papeis) {
                securityIdentity.hasRole(papel);
            }

            boolean temPermissao = papeis.stream().anyMatch(securityIdentity::hasRole);

            if (!temPermissao) {
                throw new UnauthorizedException();
            }
        }
    }

    @Override
    public void validaPessoaLogada(String codigoPessoa) {
        if (!getCodigoUsuarioLogado().equals(codigoPessoa)) {
            throw new UnauthorizedException();
        }
    }

    private String getCodigoUsuarioLogado() {
        return ((OidcJwtCallerPrincipal) securityIdentity.getPrincipal()).getSubject();
    }

    @Override
    public boolean isParceiro() {
        return securityIdentity.hasRole("PAPEL_PARCEIRO");
    }

    @Override
    public PessoaEntity getUsuarioLogado() {
        return pessoaDAO.buscaPorId(UUID.fromString(getCodigoUsuarioLogado()))
                .orElseThrow(() -> new InfraestruturaException("Usuário logado não encontrado na tabela de pessoas."));
    }

}
