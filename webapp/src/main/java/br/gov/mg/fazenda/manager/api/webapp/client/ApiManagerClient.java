package br.gov.mg.fazenda.manager.api.webapp.client;

import br.gov.mg.fazenda.manager.api.webapp.dto.ApiManagerDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public interface ApiManagerClient {

    @GetExchange
    List<ApiManagerDTO> recuperarApisGerenciadas();

    @GetExchange(value = "/{id}")
    ApiManagerDTO recuperarGerenciamentoApiPorId(@PathVariable Long id);

    @PostExchange(contentType = APPLICATION_JSON_VALUE)
    ApiManagerDTO registrarGerenciamentoApi(@RequestBody ApiManagerDTO apiManagerRequest);

    @PutExchange(value = "/{id}", contentType = APPLICATION_JSON_VALUE)
    ApiManagerDTO alterarGerenciamentoApi(@PathVariable Long id, @RequestBody ApiManagerDTO apiManagerRequest);

    @DeleteExchange(value = "/{id}")
    void excluirGerenciamentoApi(@PathVariable Long id);

}
