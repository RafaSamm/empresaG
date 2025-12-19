package br.com.rhssolutions.empresaG.exception;

public class FuncionarioNotFoundException extends RuntimeException {

    public FuncionarioNotFoundException(String message) {
        super(message);
    }
}
