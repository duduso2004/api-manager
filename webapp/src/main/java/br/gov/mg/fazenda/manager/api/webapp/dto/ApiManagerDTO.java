package br.gov.mg.fazenda.manager.api.webapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class ApiManagerDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String dataCadastro;
    private String dataAlteracao;
    private List<ApiManagerPortDTO> portasMapeadas;

}
