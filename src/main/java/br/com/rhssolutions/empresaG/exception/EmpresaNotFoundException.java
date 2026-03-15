package br.com.rhssolutions.empresaG.exception;

public class EmpresaNotFoundException extends ResourceNotFoundException {

    public EmpresaNotFoundException(String message) {
        super(message);
    }
}
