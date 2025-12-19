package br.com.rhssolutions.empresaG.exception;

public class EmpresaNotFoundException extends RuntimeException {

    public EmpresaNotFoundException(String message) {
        super(message);
    }
}
