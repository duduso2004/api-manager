package br.gov.mg.fazenda.manager.api.server.controller;

import br.gov.mg.fazenda.manager.api.server.dto.ApiManagerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@Tag(name = "Operações Referentes ao Gerenciamento de API's")
public interface ApiManagerControllerDocs {

    @Operation(summary = "Recupera todos os gerenciamentos de API's")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ApiManagerDTO.class)))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE))
    })
    ResponseEntity<List<ApiManagerDTO>> recuperarApisGerenciadas();

    @Operation(summary = "Recupera um gerenciamento de API por ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiManagerDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE))
    })
    ResponseEntity<ApiManagerDTO> recuperarGerenciamentoApiPorId(Long id);

    @Operation(summary = "Registra o gerenciamento de uma nova API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiManagerDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE))
    })
    ResponseEntity<ApiManagerDTO> registrarGerenciamentoApi(@Valid ApiManagerDTO apiManagerRequest);

    @Operation(summary = "Altera os dados de gerenciamento de uma API já cadastrada")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiManagerDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE))
    })
    ResponseEntity<ApiManagerDTO> alterarGerenciamentoApi(Long id, @Valid ApiManagerDTO apiManagerRequest);

    @Operation(summary = "Efetua a exclusão do gerenciamento de uma API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE))
    })
    ResponseEntity<Void> excluirGerenciamentoApi(Long id);

}
