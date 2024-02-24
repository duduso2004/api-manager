package br.gov.mg.fazenda.manager.api.webapp.util;

import jakarta.faces.application.FacesMessage;
import org.omnifaces.util.Messages;

import java.io.Serializable;

import static java.util.Objects.nonNull;

public class MessageUtils implements Serializable {

    public static void addDetailMessage(String message) {
        addDetailMessage(message, null);
    }

    public static void addDetailMessage(String message, FacesMessage.Severity severity) {
        final var facesMessage = Messages.create(message).get();
        if (nonNull(severity)) {
            facesMessage.setSeverity(severity);
        }
        Messages.add(null, facesMessage);
    }

}
