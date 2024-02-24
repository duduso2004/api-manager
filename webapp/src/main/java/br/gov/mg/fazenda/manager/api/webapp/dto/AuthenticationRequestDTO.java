package br.gov.mg.fazenda.manager.api.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDTO {

    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
    private String grantType;

}