package br.com.rhssolutions.empresaG.exception;

public class DepartamentoNotFoundException extends ResourceNotFoundException {

    public DepartamentoNotFoundException(String message) {
        super(message);
    }
}
