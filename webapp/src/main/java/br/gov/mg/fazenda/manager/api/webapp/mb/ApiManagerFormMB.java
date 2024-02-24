package br.gov.mg.fazenda.manager.api.webapp.mb;

import br.gov.mg.fazenda.manager.api.webapp.client.ApiManagerClient;
import br.gov.mg.fazenda.manager.api.webapp.dto.ApiManagerDTO;
import br.gov.mg.fazenda.manager.api.webapp.dto.ApiManagerPortDTO;
import br.gov.mg.fazenda.manager.api.webapp.enumeration.AmbienteEnum;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

import static br.gov.mg.fazenda.manager.api.webapp.util.MessageUtils.addDetailMessage;
import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;
import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static org.omnifaces.util.Faces.isAjaxRequest;
import static org.primefaces.PrimeFaces.current;

@Named
@ViewScoped
@RequiredArgsConstructor
public class ApiManagerFormMB {

    @Getter @Setter
    private Long apiManagerId;
    @Getter @Setter
    private ApiManagerPortDTO portaSelecionada;
    @Getter
    private ApiManagerDTO apiManager;

    private final ApiManagerClient client;

    public void init() {
        if (isAjaxRequest()){
            return;
        }
        if (isCadastro()) {
            this.apiManager = ApiManagerDTO.builder().build();
        } else {
            this.apiManager = this.client.recuperarGerenciamentoApiPorId(this.apiManagerId);
        }
    }

    public void addPorta() {
        this.portaSelecionada = ApiManagerPortDTO.builder().build();
        current().executeScript("PF('addEditPortDialog').show();");
    }

    public void editPorta(ApiManagerPortDTO porta) {
        this.portaSelecionada = porta.toBuilder().build();
        current().executeScript("PF('addEditPortDialog').show();");
    }

    public void salvarApiGerenciada() {
        if (isCadastro()) {
            final var registroCadastrado = this.client.registrarGerenciamentoApi(this.apiManager);
            this.apiManagerId = registroCadastrado.getId();
        } else {
            this.client.alterarGerenciamentoApi(this.apiManagerId, this.apiManager);
        }
        addDetailMessage("Gerenciamento de Api Salvo Com Sucesso!", SEVERITY_INFO);
    }

    public String voltar() {
        return "/restrito/apis-gerenciadas-list.faces?faces-redirect=true";
    }

    public List<AmbienteEnum> getAmbientes() {
        return asList(AmbienteEnum.values());
    }

    private boolean isCadastro() {
        return isNull(this.apiManagerId);
    }

}
