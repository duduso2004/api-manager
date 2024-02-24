package br.gov.mg.fazenda.manager.api.webapp.configuration;

import br.gov.mg.fazenda.manager.api.webapp.dto.AuthenticationRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationRequest {

    @Value("${keycloak.authentication.client-id}")
    private String clientId;

    @Value("${keycloak.authentication.client-secret}")
    private String clientSecret;

    @Bean
    public AuthenticationRequestDTO authenticationRequestDTO() {
        return AuthenticationRequestDTO.builder()
                .clientId(this.clientId)
                .clientSecret(this.clientSecret)
                .grantType("password")
                .build();
    }

}
