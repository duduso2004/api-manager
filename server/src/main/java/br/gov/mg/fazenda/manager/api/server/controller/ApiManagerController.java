package br.gov.mg.fazenda.manager.api.server.controller;

import br.gov.mg.fazenda.manager.api.server.converter.ApiManagerConverter;
import br.gov.mg.fazenda.manager.api.server.dto.ApiManagerDTO;
import br.gov.mg.fazenda.manager.api.server.dto.Request;
import br.gov.mg.fazenda.manager.api.server.dto.Response;
import br.gov.mg.fazenda.manager.api.server.service.ApiManagerService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-manager")
public class ApiManagerController implements ApiManagerControllerDocs {

    private final ApiManagerService service;
    private final ApiManagerConverter converter;

    @Override
    @JsonView(Response.class)
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ApiManagerDTO>> recuperarApisGerenciadas() {
        return ResponseEntity.ok(this.service.recuperarTodas().stream().map(this.converter::fromEntity).toList());
    }

    @Override
    @JsonView(Response.class)
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiManagerDTO> recuperarGerenciamentoApiPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.converter.fromEntity(this.service.obterPorId(id)));
    }

    @Override
    @JsonView(Response.class)
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiManagerDTO> registrarGerenciamentoApi(@RequestBody @JsonView(Request.class) ApiManagerDTO apiManagerRequest) {
        final var apiManagerEntity = this.converter.toEntity(apiManagerRequest);
        return ResponseEntity.status(CREATED).body(this.converter.fromEntity(this.service.salvar(apiManagerEntity)));
    }

    @Override
    @JsonView(Response.class)
    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiManagerDTO> alterarGerenciamentoApi(@PathVariable Long id, @RequestBody @JsonView(Request.class) ApiManagerDTO apiManagerRequest) {
        final var apiManagerEntity = this.service.obterPorId(id);
        final var apiManagerEntityAlterado = this.converter.updateValues(apiManagerRequest, apiManagerEntity);
        return ResponseEntity.ok(this.converter.fromEntity(this.service.alterar(apiManagerEntityAlterado)));
    }

    @Override
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> excluirGerenciamentoApi(@PathVariable Long id) {
        this.service.excluir(id);
        return ResponseEntity.ok().build();
    }

}
