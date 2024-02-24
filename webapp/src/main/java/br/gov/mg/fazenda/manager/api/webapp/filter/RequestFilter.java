package br.gov.mg.fazenda.manager.api.webapp.filter;

import br.gov.mg.fazenda.manager.api.webapp.mb.SessionMB;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class RequestFilter implements Filter {

    private final SessionMB sessionMB;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        final var request = (HttpServletRequest) req;
        final var response = (HttpServletResponse) resp;
        if (request.getRequestURI().equals(request.getContextPath() + "/")) {
            response.sendRedirect(request.getContextPath() + "/login.faces");
        } else if (request.getRequestURI().contains("/restrito") && isNull(this.sessionMB.getUsuarioAutenticadoLogin())) {
            response.sendRedirect(request.getContextPath() + "/login.faces");
        } else {
            chain.doFilter(req, resp);
        }
    }

}