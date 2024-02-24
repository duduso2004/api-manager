package br.gov.mg.fazenda.manager.api.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class ApiManagerDTO {

    @JsonView(Response.class)
    @Schema(description = "Identificador único do registro no sistema", example = "25")
    private Long id;

    @JsonView(Request.class)
    @NotBlank(message = "O campo 'nome' é obrigatório")
    @Schema(description = "Nome da API/Micro Serviço", example = "ms-extrato-debito")
    private String nome;

    @JsonView(Request.class)
    @NotBlank(message = "O campo 'descricao' é obrigatório")
    @Schema(description = "Descrição da API/Micro Serviço", example = "API Para Geração do Extrato de Débitos de IPVA")
    private String descricao;

    @JsonView(Response.class)
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Schema(description = "Data/Hora de registro da API/Micro Serviço no sistema", example = "01-01-2023 12:00:00", type = "string")
    private LocalDateTime dataCadastro;

    @JsonView(Response.class)
    @JsonFormat(shape = STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Schema(description = "Data/Hora de alteração dos dados da API/Micro Serviço no sistema", example = "25-03-2023 17:00:00", type = "string")
    private LocalDateTime dataAlteracao;

    @Builder.Default
    @JsonView({Request.class, Response.class})
    @Schema(description = "Relação de portas mapeadas por ambiente")
    private List<ApiManagerPortDTO> portasMapeadas = new ArrayList<>();

}
