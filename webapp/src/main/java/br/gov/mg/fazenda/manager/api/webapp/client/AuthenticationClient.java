package br.gov.mg.fazenda.manager.api.webapp.client;

import br.gov.mg.fazenda.manager.api.webapp.dto.AuthenticationResponseDTO;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

public interface AuthenticationClient {

    @PostExchange(contentType = APPLICATION_FORM_URLENCODED_VALUE)
    AuthenticationResponseDTO authenticate(@RequestBody LinkedMultiValueMap<String, String> data);

}
