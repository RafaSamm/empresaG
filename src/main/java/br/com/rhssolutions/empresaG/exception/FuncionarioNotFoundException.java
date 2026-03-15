package br.com.rhssolutions.empresaG.exception;

public class FuncionarioNotFoundException extends ResourceNotFoundException {

    public FuncionarioNotFoundException(String message) {
        super(message);
    }
}
