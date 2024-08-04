package br.com.rhssolutions.empresaG.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Erro não esperado!! Verifique o log da aplicação!!", e);
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
