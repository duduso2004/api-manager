package br.gov.mg.fazenda.manager.api.webapp.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AmbienteEnum {

    DEV("Desenvolvimento"),
    HOM("Homologação"),
    PRO("Produção");

    private final String descricao;

}
