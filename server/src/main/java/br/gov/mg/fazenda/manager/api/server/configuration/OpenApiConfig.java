package br.gov.mg.fazenda.manager.api.server.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
		title = "API Manager",
		version = "1.0.0",
		description = "<img src='http://www.fazenda.mg.gov.br/export/system/modules/org.fazenda.mg.gov.br/resources/img/logo2.png'/>",
		contact = @Contact(name = "Eduardo Santos de Oliveira", email = "eduoliveira.fip@gmail.com"))
)
public class OpenApiConfig {

}
