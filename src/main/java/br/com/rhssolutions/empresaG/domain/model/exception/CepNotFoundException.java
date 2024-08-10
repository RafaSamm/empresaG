package br.com.rhssolutions.empresaG.domain.model.exception;


public class CepNotFoundException extends RuntimeException {

    public CepNotFoundException(String message) {
        super(message);
    }

}
