package br.com.ifba.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// Permite que a classe lide com exceções de qualquer controlador REST na aplicação.
@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    // Verificação para incluir o stacktrace na resposta de erro.
    @Value(value = "${server.error.include-exception}")
    private boolean printStackTrace;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBusinessException(
            final BusinessException businessException,
            final WebRequest request) {

        final String mensagemErro = businessException.getMessage();

        // Registra a mensagem e a exceção para depuração.
        log.error(mensagemErro, businessException);

        // Retorna a resposta de erro, delegando a construção do objeto de erro.
        return construirMensagemDeErro(
                businessException,
                mensagemErro,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    private ResponseEntity<Object> construirMensagemDeErro(
            final BusinessException exception,
            final String message,
            final HttpStatus httpStatus,
            final WebRequest request) {

        // Cria um novo objeto DTO de resposta de erro.
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);

        // Verifica se a flag de 'printStackTrace' está ativa.
        if (this.printStackTrace) {
            // Usa o Apache Commons Lang para obter a pilha de erros (stacktrace) e a adiciona na resposta.
            errorResponse.setStacktrace(ExceptionUtils.getStackTrace(exception));
        }

        // Retorna o ResponseEntity com o status e o corpo de erro.
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationExceptionDetails> handleMethodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        // Extrai a lista de erros de validação do objeto de exceção.
        List<FieldError> fieldErrors = methodArgumentNotValidException
                .getBindingResult().getFieldErrors();

        // Coleta os nomes dos campos com erro em uma única string.
        String fields = fieldErrors.stream().map(FieldError::getField)
                .collect(Collectors.joining(", "));

        // Coleta as mensagens de erro de cada campo em uma única string.
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        // Constrói e retorna o objeto de resposta de erro padronizado (DTO).
        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Campos inválidos.")
                        .details("Cheque o(s) campo(s) com erros")
                        .developerMessage(methodArgumentNotValidException.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST
        );
    }
}