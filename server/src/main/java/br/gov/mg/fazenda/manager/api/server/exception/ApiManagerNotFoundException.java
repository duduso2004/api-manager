package br.gov.mg.fazenda.manager.api.server.exception;

public class ApiManagerNotFoundException extends RuntimeException {

    public ApiManagerNotFoundException() {
        super("Não foi localizado registro de gerenciamento de API/Micro Serviço com o ID informado.");
    }

}
