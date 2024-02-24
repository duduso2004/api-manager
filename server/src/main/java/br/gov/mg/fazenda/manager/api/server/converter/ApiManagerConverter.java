package br.gov.mg.fazenda.manager.api.server.converter;

import br.gov.mg.fazenda.manager.api.server.dto.ApiManagerDTO;
import br.gov.mg.fazenda.manager.api.server.dto.ApiManagerPortDTO;
import br.gov.mg.fazenda.manager.api.server.entity.ApiManager;
import br.gov.mg.fazenda.manager.api.server.entity.ApiManagerPort;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class ApiManagerConverter {

    public ApiManagerDTO fromEntity(ApiManager apiManager) {
        return ApiManagerDTO.builder()
                .id(apiManager.getId())
                .nome(apiManager.getNome())
                .descricao(apiManager.getDescricao())
                .dataCadastro(apiManager.getDataCadastro())
                .dataAlteracao(apiManager.getDataAlteracao())
                .portasMapeadas(apiManager.getPortas().stream().map(this::fromEntity).toList())
                .build();
    }

    public ApiManager toEntity(ApiManagerDTO apiManagerDTO) {
        final var apiManager = ApiManager.builder()
                .id(apiManagerDTO.getId())
                .nome(apiManagerDTO.getNome())
                .descricao(apiManagerDTO.getDescricao())
                .dataCadastro(apiManagerDTO.getDataCadastro())
                .dataAlteracao(apiManagerDTO.getDataAlteracao())
                .build();
        if (nonNull(apiManagerDTO.getPortasMapeadas())) {
            apiManager.setPortas(apiManagerDTO
                    .getPortasMapeadas()
                    .stream()
                    .map(porta -> toEntity(apiManager, porta))
                    .toList());
        }
        return apiManager;
    }

    public ApiManager updateValues(ApiManagerDTO apiManagerDTO, ApiManager apiManager) {
        return apiManager.toBuilder()
                .nome(apiManagerDTO.getNome())
                .descricao(apiManagerDTO.getDescricao())
                .portas(apiManagerDTO
                        .getPortasMapeadas()
                        .stream()
                        .map(porta -> toEntity(apiManager, porta))
                        .toList())
                .build();
    }

    private ApiManagerPortDTO fromEntity(ApiManagerPort apiManagerPort) {
        return ApiManagerPortDTO.builder()
                .id(apiManagerPort.getId())
                .porta(apiManagerPort.getPorta())
                .linkSwagger(apiManagerPort.getLinkSwagger())
                .ambiente(apiManagerPort.getAmbiente())
                .secretDbStatus(apiManagerPort.getSecretDbStatus())
                .hostName(apiManagerPort.getHostName())
                .build();
    }

    private ApiManagerPort toEntity(ApiManager apiManager, ApiManagerPortDTO apiManagerPortDTO) {
        return ApiManagerPort.builder()
                .id(apiManagerPortDTO.getId())
                .porta(apiManagerPortDTO.getPorta())
                .linkSwagger(apiManagerPortDTO.getLinkSwagger())
                .ambiente(apiManagerPortDTO.getAmbiente())
                .secretDbStatus(apiManagerPortDTO.getSecretDbStatus())
                .hostName(apiManagerPortDTO.getHostName())
                .apiManager(apiManager)
                .build();
    }

}
