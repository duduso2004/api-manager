package br.gov.mg.fazenda.manager.api.server.dto;

import br.gov.mg.fazenda.manager.api.server.enumeration.AmbienteEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class ApiManagerPortDTO {

    @JsonView(Response.class)
    @Schema(description = "Identificador único do registro no sistema", example = "88")
    private Long id;

    @JsonView(Request.class)
    @NotNull(message = "O campo 'porta' é obrigatório")
    @Schema(description = "Número da porta utilizada pela API/Micro Serviço no ambiente", example = "32008")
    private Integer porta;

    @JsonView(Request.class)
    @Schema(description = "Link de acesso ao Swagger da aplicação (caso exista)", example = "http://172.23.201.52:32008/swagger-ui.html")
    private String linkSwagger;

    @JsonView(Request.class)
    @NotNull(message = "O campo 'ambiente' é obrigatório")
    @Schema(description = "Ambiente a que se refere a porta declarada", example = "DEV")
    private AmbienteEnum ambiente;

    @JsonView(Request.class)
    @Schema(description = "Status da solicitação de criação das secrets de banco para o ambiente", example = "OK")
    private String secretDbStatus;

    @JsonView(Request.class)
    @Schema(description = "Nome do host onde a API/Micro Serviço está hospedado por ambiente", example = "ms-extrato-debito.fazenda.mg.gov.br")
    private String hostName;

}
