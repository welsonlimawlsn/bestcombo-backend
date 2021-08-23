package br.com.bestcombo.adapters.seguranca.service;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

public class OAuthConfig {

    @Inject
    AuthzClient authzClient;

    @ApplicationScoped
    RealmResource provide() {
        Configuration configuration = authzClient.getConfiguration();
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(configuration.getAuthServerUrl())
                .realm(configuration.getRealm())
                .clientId(configuration.getResource())
                .clientSecret((String) configuration.getCredentials().get("secret"))
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();

        return keycloak.realm(configuration.getRealm());
    }

}
