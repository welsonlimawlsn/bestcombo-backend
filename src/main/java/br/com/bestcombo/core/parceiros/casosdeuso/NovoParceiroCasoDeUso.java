package br.com.bestcombo.core.parceiros.casosdeuso;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.bestcombo.core.casosdeuso.AbstractCasoDeUso;
import br.com.bestcombo.core.casosdeuso.anotacao.CasoDeUso;
import br.com.bestcombo.core.casosdeuso.enums.CasosDeUso;
import br.com.bestcombo.core.enderecos.modelos.Endereco;
import br.com.bestcombo.core.exception.NegocioException;
import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRequisicaoDTO;
import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRespostaDTO;
import br.com.bestcombo.core.parceiros.entidade.ParceiroEntity;
import br.com.bestcombo.ports.dao.ParceiroDAO;
import br.com.bestcombo.ports.service.EnderecoService;
import br.com.bestcombo.ports.service.UsuarioOAuthService;

@ApplicationScoped
@Slf4j
@CasoDeUso(CasosDeUso.NOVO_PARCEIRO)
public class NovoParceiroCasoDeUso extends AbstractCasoDeUso<NovoParceiroRequisicaoDTO, NovoParceiroRespostaDTO> {

    @Inject
    EnderecoService enderecoService;

    @Inject
    ParceiroDAO parceiroDAO;

    @Inject
    UsuarioOAuthService usuarioOAuthService;


    @Override
    protected void processa(NovoParceiroRequisicaoDTO requisicao, NovoParceiroRespostaDTO resposta) throws NegocioException {
        Endereco endereco = enderecoService.buscaEnderecoPorCep(requisicao.getEndereco().getCep());

        usuarioOAuthService.criaNovoUsuario(requisicao, resposta);

        ParceiroEntity parceiroEntity = criaParceiro(requisicao, resposta);

        parceiroDAO.persiste(parceiroEntity);

        resposta.setCodigo(parceiroEntity.getCodigo().toString());
    }

    private ParceiroEntity criaParceiro(NovoParceiroRequisicaoDTO requisicao, NovoParceiroRespostaDTO resposta) {
        return ParceiroEntity.builder()
                .cpfCnpj(requisicao.getCpfCnpj())
                .email(requisicao.getEmail())
                .nome(requisicao.getNome())
                .senha(requisicao.getSenha())
                .codigo(UUID.fromString(resposta.getCodigo()))
                .build();
    }

}

