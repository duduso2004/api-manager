package br.gov.mg.fazenda.manager.api.server.service;

import br.gov.mg.fazenda.manager.api.server.entity.ApiManager;
import br.gov.mg.fazenda.manager.api.server.exception.ApiManagerNotFoundException;
import br.gov.mg.fazenda.manager.api.server.repository.ApiManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class ApiManagerService {

    private final ApiManagerRepository repository;

    public List<ApiManager> recuperarTodas() {
        return this.repository.findAll();
    }

    public ApiManager obterPorId(Long id) {
        return this.repository.findById(id).orElseThrow(ApiManagerNotFoundException::new);
    }

    public ApiManager salvar(ApiManager apiManager) {
        apiManager.setDataCadastro(LocalDateTime.now());
        return this.repository.save(apiManager);
    }

    public ApiManager alterar(ApiManager apiManager) {
        requireNonNull(apiManager.getId());
        if (!this.repository.existsById(apiManager.getId())) {
            throw new ApiManagerNotFoundException();
        }
        final var dataHoraAtual = LocalDateTime.now();
        apiManager.setDataAlteracao(dataHoraAtual);
        return this.repository.save(apiManager);
    }

    public void excluir(Long id) {
        this.repository.delete(obterPorId(id));
    }

}
