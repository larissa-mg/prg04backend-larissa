package br.com.ifba.infrastructure.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

/**
 * DTO para padronizar a resposta de erro de validação de campos.
 * Esta classe é utilizada pelo ApiExceptionHandler para formatar a resposta
 * quando uma requisição com dados inválidos (@Valid) é recebida.
 * O objetivo é fornecer detalhes claros sobre quais campos estão com erro
 * e as respectivas mensagens de validação.
 */
@Data
@SuperBuilder
public class ValidationExceptionDetails {
    protected String title;
    protected int status;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;
    protected String fields;
    protected String fieldsMessage;
}