package br.gov.mg.fazenda.manager.api.webapp.configuration;

import br.gov.mg.fazenda.manager.api.webapp.client.ApiManagerClient;
import br.gov.mg.fazenda.manager.api.webapp.client.AuthenticationClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.web.reactive.function.client.support.WebClientAdapter.create;
import static org.springframework.web.service.invoker.HttpServiceProxyFactory.builder;

@Configuration
public class WebClientConfig {

    @Bean
    ApiManagerClient apiManagerClient(WebClient.Builder builder, @Value("${api.manager.server.url}") String baseUrl) {
        return builder()
                .exchangeAdapter(create(builder.baseUrl(baseUrl).build()))
                .build()
                .createClient(ApiManagerClient.class);
    }

    @Bean
    AuthenticationClient authenticationClient(WebClient.Builder builder, @Value("${keycloak.authentication.url}") String baseUrl) {
        return builder()
                .exchangeAdapter(create(builder.baseUrl(baseUrl).build()))
                .build()
                .createClient(AuthenticationClient.class);
    }

}
