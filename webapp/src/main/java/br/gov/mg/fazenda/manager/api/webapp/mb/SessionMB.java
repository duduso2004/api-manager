package br.gov.mg.fazenda.manager.api.webapp.mb;

import jakarta.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionMB {

    @Getter @Setter
    private String usuarioAutenticadoLogin;
    @Getter @Setter
    private String usuarioAutenticadoNome;

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.faces?faces-redirect=true";
    }

}
