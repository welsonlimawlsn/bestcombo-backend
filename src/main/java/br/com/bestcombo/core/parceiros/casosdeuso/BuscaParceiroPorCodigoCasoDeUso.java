package br.com.bestcombo.core.parceiros.casosdeuso;

import io.quarkus.security.UnauthorizedException;
import io.quarkus.security.identity.SecurityIdentity;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.exception.Erro;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.parceiros.dto.buscaparceiroporcodigo.BuscaParceiroPorCodigoRequisicaoDTO;
import br.com.bestcombo.core.parceiros.dto.buscaparceiroporcodigo.BuscaParceiroPorCodigoRespostaDTO;
import br.com.bestcombo.core.parceiros.entidade.ParceiroEntity;
import br.com.bestcombo.ports.dao.ParceiroDAO;

@CasoDeUso(CasosDeUso.BUSCA_PARCEIRO_POR_CODIGO)
@ApplicationScoped
public class BuscaParceiroPorCodigoCasoDeUso extends AbstractCasoDeUso<BuscaParceiroPorCodigoRequisicaoDTO, BuscaParceiroPorCodigoRespostaDTO> {

    @Inject
    ParceiroDAO parceiroDAO;

    @Inject
    SecurityIdentity securityIdentity;

    @Override
    protected void processa(BuscaParceiroPorCodigoRequisicaoDTO requisicao, BuscaParceiroPorCodigoRespostaDTO resposta) throws NegocioException {
        boolean parceiros = securityIdentity.hasRole("parceiros");

        if (!parceiros) {
            throw new UnauthorizedException();
        }

        ParceiroEntity parceiroEntity = parceiroDAO.buscaPorId(requisicao.getCodigo())
                .orElseThrow(() -> new NegocioException(Erro.PARCEIRO_NAO_ENCONTRADO));

        mapperParaDTO(parceiroEntity, resposta);
    }

    private void mapperParaDTO(ParceiroEntity parceiroEntity, BuscaParceiroPorCodigoRespostaDTO resposta) {
        resposta.setEmail(parceiroEntity.getEmail());
        resposta.setCpfCnpj(parceiroEntity.getCpfCnpj());
        resposta.setId(parceiroEntity.getCodigo().toString());
        resposta.setNome(parceiroEntity.getNome());
    }

}
