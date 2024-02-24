package br.gov.mg.fazenda.manager.api.webapp.mb;

import br.gov.mg.fazenda.manager.api.webapp.client.AuthenticationClient;
import br.gov.mg.fazenda.manager.api.webapp.dto.AuthenticationRequestDTO;
import br.gov.mg.fazenda.manager.api.webapp.dto.AuthenticationResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Optional;

import static br.gov.mg.fazenda.manager.api.webapp.util.MessageUtils.addDetailMessage;
import static io.micrometer.common.util.StringUtils.isNotBlank;
import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static java.util.Optional.ofNullable;
import static org.apache.tomcat.util.codec.binary.Base64.decodeBase64;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Named
@ViewScoped
@RequiredArgsConstructor
public class AutenticacaoMB {

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String AUTHENTICATED_USER_LOGIN = "preferred_username";
    private static final String AUTHENTICATED_USER_NAME = "name";
    private static final String USUARIO_SENHA_INVALIDOS = "Usuário ou Senha Inválidos";

    @Getter @Setter
    private String usuario;
    @Getter @Setter
    private String senha;

    @Value("${keycloak.authentication.public-key}")
    private String publicKey;

    private final SessionMB sessionMB;
    private final ObjectMapper mapper;
    private final AuthenticationClient authenticationClient;
    private final AuthenticationRequestDTO authenticationRequest;

    public String entrar() {
        try {
            this.authenticationRequest.setUsername(this.usuario);
            this.authenticationRequest.setPassword(this.senha);
            final var response = this.authenticationClient.authenticate(getAuthenticationPayload());
            final var claims = getClaimsFromToken(response.getAccessToken());
            this.sessionMB.setUsuarioAutenticadoLogin(claims.get(AUTHENTICATED_USER_LOGIN, String.class));
            this.sessionMB.setUsuarioAutenticadoNome(claims.get(AUTHENTICATED_USER_NAME, String.class));
            return "restrito/index.faces?faces-redirect=true";
        } catch (WebClientResponseException wex) {
            if (UNAUTHORIZED.equals(wex.getStatusCode())) {
                addDetailMessage(USUARIO_SENHA_INVALIDOS, SEVERITY_ERROR);
            } else if (isNotBlank(wex.getResponseBodyAsString())) {
                final var responseBody = wex.getResponseBodyAs(AuthenticationResponseDTO.class);
                addDetailMessage(ofNullable(responseBody).map(AuthenticationResponseDTO::getError).orElse(wex.getMessage()), SEVERITY_ERROR);
            } else {
                addDetailMessage(wex.getMessage(), SEVERITY_ERROR);
            }
            return null;
        }
    }

    @SneakyThrows
    private AuthenticationResponseDTO parseResponseBody(byte[] responseBody) {
        return this.mapper.readValue(responseBody, AuthenticationResponseDTO.class);
    }

    @SneakyThrows
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getParsedPublicKey().get()).parseClaimsJws(token).getBody();
    }

    @SneakyThrows
    private Optional<RSAPublicKey> getParsedPublicKey() {
        final var keySpecX509 = new X509EncodedKeySpec(decodeBase64(this.publicKey));
        final var rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpecX509);
        return Optional.of(rsaPublicKey);
    }

    private LinkedMultiValueMap<String, String> getAuthenticationPayload() {
        final var payload = new LinkedMultiValueMap<String, String>();
        payload.set(CLIENT_ID, this.authenticationRequest.getClientId());
        payload.set(CLIENT_SECRET, this.authenticationRequest.getClientSecret());
        payload.set(GRANT_TYPE, this.authenticationRequest.getGrantType());
        payload.set(USERNAME, this.authenticationRequest.getUsername());
        payload.set(PASSWORD, this.authenticationRequest.getPassword());
        return payload;
    }

}
