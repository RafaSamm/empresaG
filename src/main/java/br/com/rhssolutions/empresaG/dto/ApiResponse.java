package br.com.rhssolutions.empresaG.dto;

import java.time.LocalDateTime;

public record ApiResponse<T>(   //Resposta padronizada para os controllers
                                int status,
                                String message,
                                T data,
                                LocalDateTime timestamp
) {
}
