package br.com.bestcombo.adapters.seguranca.service;

import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import br.com.bestcombo.core.exception.InfraestruturaException;
import br.com.bestcombo.core.pessoas.dto.novapessoa.NovaPessoaRequisicaoDTO;
import br.com.bestcombo.core.pessoas.dto.novapessoa.NovaPessoaRespostaDTO;
import br.com.bestcombo.ports.service.UsuarioOAuthService;

@ApplicationScoped
public class UsuarioOAuthServiceImpl implements UsuarioOAuthService {

    @Inject
    RealmResource realmResource;

    @Override
    public String criaNovoUsuario(NovaPessoaRequisicaoDTO<? extends NovaPessoaRespostaDTO> requisicao, String grupo) {
        try {
            UsersResource usersResource = realmResource.users();

            GroupRepresentation grupoParceiro = getGrupoParceiros(grupo);

            String idUsuario = criaUsuarioKeycloak(requisicao, usersResource);

            UserResource userResource = usersResource.get(idUsuario);

            userResource.resetPassword(getCredentials(requisicao));

            userResource.joinGroup(grupoParceiro.getId());

            return idUsuario;
        } catch (Exception e) {
            throw new InfraestruturaException("Erro ao criar usuario Keycloak.", e);
        }
    }

    private GroupRepresentation getGrupoParceiros(String grupo) {
        return realmResource.groups().groups().stream()
                .filter(g -> g.getName().equals(grupo))
                .findFirst()
                .orElseThrow(() -> new InfraestruturaException("Erro ao recuperar o grupo usuario_externo_parceiro"));
    }

    private String criaUsuarioKeycloak(NovaPessoaRequisicaoDTO<? extends NovaPessoaRespostaDTO> requisicao, UsersResource usersResource) {
        Response response = usersResource.create(mapperParaUserRepresentation(requisicao));

        if (!Arrays.asList(200, 201).contains(response.getStatus())) {
            throw new InfraestruturaException("Não foi possivel criar o usuario no KeyCloak. " + getMensagemErro(response));
        }

        return ((String) response.getHeaders().get("Location").get(0)).split("/")[8];
    }

    private String getMensagemErro(Response response) {
        try {
            return new String(((FilterInputStream) response.getEntity()).readAllBytes());
        } catch (IOException e) {
            throw new InfraestruturaException("Erro ao ler erro do Keycloak.", e);
        }
    }

    private UserRepresentation mapperParaUserRepresentation(NovaPessoaRequisicaoDTO<? extends NovaPessoaRespostaDTO> requisicao) {
        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(requisicao.getUsuario());
        userRepresentation.setEmail(requisicao.getEmail());
        userRepresentation.setFirstName(requisicao.getNome());
        userRepresentation.setLastName(requisicao.getSobrenome());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        return userRepresentation;
    }

    private CredentialRepresentation getCredentials(NovaPessoaRequisicaoDTO<? extends NovaPessoaRespostaDTO> requisicao) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(requisicao.getSenha());
        credentialRepresentation.setTemporary(false);
        return credentialRepresentation;
    }

}
