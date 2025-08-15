package br.com.ifba.infrastructure.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO para padronizar as respostas de erro da API.
 * Contém informações sobre o status HTTP, a mensagem de erro e, opcionalmente, o stacktrace.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
    private String stacktrace;

    // Construtor usado para criar uma resposta de erro sem o stacktrace
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}