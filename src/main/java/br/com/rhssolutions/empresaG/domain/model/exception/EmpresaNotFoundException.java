package br.com.rhssolutions.empresaG.domain.model.exception;

public class EmpresaNotFoundException extends RuntimeException {

    public EmpresaNotFoundException(String message) {
        super(message);
    }
}
