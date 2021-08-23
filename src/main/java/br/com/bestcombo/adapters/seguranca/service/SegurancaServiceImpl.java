package br.com.bestcombo.adapters.seguranca.service;

import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.CasoDeUsoEntity;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.dto.RequisicaoDTO;
import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.ports.dao.CasoDeUsoDAO;
import br.com.bestcombo.ports.dao.PapelDAO;
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

    @Override
    public void verificaAutorizacao(RequisicaoDTO<?> requisicao) {
        CasoDeUso casoDeUso = AnotacaoUtil.getAnotacao(requisicao, CasoDeUso.class);

        CasoDeUsoEntity casoDeUsoEntity = casoDeUsoDAO.buscaPorId(casoDeUso.value().getCodigo())
                .orElseThrow(() -> new InfraestruturaException(String.format("O caso de uso %s não está presente no banco de dados.", casoDeUso.value())));

        List<String> papeis = papelDAO.buscaPapeisPorCasoDeUso(casoDeUsoEntity);

        for (String papel : papeis) {
            securityIdentity.hasRole(papel);
        }

        boolean temPermissao = papeis.stream().anyMatch(securityIdentity::hasRole);

        if (!temPermissao) {
            throw new UnauthorizedException();
        }
    }

}
