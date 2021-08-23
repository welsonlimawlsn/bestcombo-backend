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
import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRequisicaoDTO;
import br.com.bestcombo.core.parceiros.dto.novoparceiro.NovoParceiroRespostaDTO;
import br.com.bestcombo.ports.service.UsuarioOAuthService;

@ApplicationScoped
public class UsuarioOAuthServiceImpl implements UsuarioOAuthService {

    @Inject
    RealmResource realmResource;

    @Override
    public void criaNovoUsuario(NovoParceiroRequisicaoDTO requisicao, NovoParceiroRespostaDTO resposta) {
        try {
            UsersResource usersResource = realmResource.users();

            GroupRepresentation grupoParceiro = getGrupoParceiros();

            String idUsuario = criaUsuarioKeycloak(requisicao, usersResource);

            UserResource userResource = usersResource.get(idUsuario);

            userResource.resetPassword(getCredentials(requisicao));

            userResource.joinGroup(grupoParceiro.getId());

            resposta.setCodigo(idUsuario);
        } catch (Exception e) {
            throw new InfraestruturaException("Erro ao criar usuario Keycloak.", e);
        }
    }

    private GroupRepresentation getGrupoParceiros() {
        return realmResource.groups().groups().stream()
                .filter(g -> g.getName().equals("usuario_externo_parceiro"))
                .findFirst()
                .orElseThrow(() -> new InfraestruturaException("Erro ao recuperar o grupo usuario_externo_parceiro"));
    }

    private String criaUsuarioKeycloak(NovoParceiroRequisicaoDTO requisicao, UsersResource usersResource) {
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

    private UserRepresentation mapperParaUserRepresentation(NovoParceiroRequisicaoDTO requisicao) {
        UserRepresentation userRepresentation = new UserRepresentation();
        String[] nomesSeparado = requisicao.getNome().split(" ");

        userRepresentation.setUsername(requisicao.getEmail());
        userRepresentation.setEmail(requisicao.getEmail());
        userRepresentation.setFirstName(nomesSeparado[0]);
        userRepresentation.setLastName(nomesSeparado[nomesSeparado.length - 1]);
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        return userRepresentation;
    }

    private CredentialRepresentation getCredentials(NovoParceiroRequisicaoDTO requisicao) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(requisicao.getSenha());
        credentialRepresentation.setTemporary(false);
        return credentialRepresentation;
    }

}
