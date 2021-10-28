package br.com.bestcombo.adapters.seguranca.service;

import io.quarkus.oidc.runtime.OidcJwtCallerPrincipal;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.CasoDeUsoEntity;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.core.pessoas.entity.PessoaEntity;
import br.com.bestcombo.core.pessoas.enums.TipoPessoa;
import br.com.bestcombo.core.seguranca.anotacao.Autoriza;
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
        List<String> papeis = getAutorizadosCasoDeUso(requisicao);

        if (!papeis.isEmpty() && !papeis.contains(TipoPessoa.PUBLICO.getPapel())) {
            boolean temPermissao = papeis.stream().anyMatch(securityIdentity::hasRole);

            if (!temPermissao) {
                throw new UnauthorizedException();
            }
        }
    }

    private List<String> getAutorizadosCasoDeUso(RequisicaoDTO<?> requisicao) {
        try {
            Autoriza autorizados = AnotacaoUtil.getAnotacao(requisicao, Autoriza.class);

            return Arrays.stream(autorizados.value())
                    .map(TipoPessoa::getPapel)
                    .collect(Collectors.toList());

        } catch (InfraestruturaException e) {
            CasoDeUso casoDeUso = AnotacaoUtil.getAnotacao(requisicao, CasoDeUso.class);

            CasoDeUsoEntity casoDeUsoEntity = casoDeUsoDAO.buscaPorId(casoDeUso.value().getCodigo())
                    .orElseThrow(() -> new InfraestruturaException(String.format("O caso de uso %s não está presente no banco de dados.", casoDeUso.value())));
            return papelDAO.buscaPapeisPorCasoDeUso(casoDeUsoEntity);
        }
    }

    @Override
    public void validaPessoaLogada(UUID codigoPessoa) {
        if (!getCodigoUsuarioLogado().equals(codigoPessoa)) {
            throw new UnauthorizedException();
        }
    }

    @Override
    public UUID getCodigoUsuarioLogado() {
        return UUID.fromString(((OidcJwtCallerPrincipal) securityIdentity.getPrincipal()).getSubject());
    }

    @Override
    public boolean isCliente() {
        return securityIdentity.hasRole("PAPEL_CLIENTE");
    }

    @Override
    public boolean isParceiro() {
        return securityIdentity.hasRole("PAPEL_PARCEIRO");
    }

    @Override
    public PessoaEntity getUsuarioLogado() {
        return pessoaDAO.buscaPorId(getCodigoUsuarioLogado())
                .orElseThrow(() -> new InfraestruturaException("Usuário logado não encontrado na tabela de pessoas."));
    }

}
