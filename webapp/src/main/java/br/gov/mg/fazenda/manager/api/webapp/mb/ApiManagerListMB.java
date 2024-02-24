package br.gov.mg.fazenda.manager.api.webapp.mb;

import br.gov.mg.fazenda.manager.api.webapp.client.ApiManagerClient;
import br.gov.mg.fazenda.manager.api.webapp.dto.ApiManagerDTO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import static br.gov.mg.fazenda.manager.api.webapp.util.MessageUtils.addDetailMessage;
import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

@Named
@ViewScoped
@RequiredArgsConstructor
public class ApiManagerListMB {

    @Getter @Setter
    private ApiManagerDTO apiSelecionada;
    @Getter
    private List<ApiManagerDTO> apisGerenciadas;

    private final ApiManagerClient client;

    @PostConstruct
    public void postConstruct() {
        this.apisGerenciadas = this.client.recuperarApisGerenciadas();
    }

    public void excluir() {
        this.client.excluirGerenciamentoApi(this.apiSelecionada.getId());
        this.apisGerenciadas.remove(this.apiSelecionada);
        this.apiSelecionada = null;
        addDetailMessage("Registro Excluído Com Sucesso!", SEVERITY_INFO);
    }

    public String editar(Long id) {
        return "/restrito/apis-gerenciadas-form.faces?id=%s&faces-redirect=true".formatted(id);
    }

}
