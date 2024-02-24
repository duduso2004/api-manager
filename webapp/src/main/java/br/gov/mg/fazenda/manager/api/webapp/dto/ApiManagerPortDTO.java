package br.gov.mg.fazenda.manager.api.webapp.dto;

import br.gov.mg.fazenda.manager.api.webapp.enumeration.AmbienteEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class ApiManagerPortDTO {

    private Long id;
    private Integer porta;
    private String linkSwagger;
    private AmbienteEnum ambiente;
    private String secretDbStatus;
    private String hostName;

}
