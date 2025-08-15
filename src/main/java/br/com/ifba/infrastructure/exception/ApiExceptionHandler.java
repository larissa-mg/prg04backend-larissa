package br.com.ifba.infrastructure.exception;

// Importa classes para manipulação de exceções e requisições.
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Permite que a classe lide com exceções de qualquer controlador REST na aplicação.
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

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
        logger.error(mensagemErro, businessException);

        // Retorna a resposta de erro, delegando a construção do objeto de erro.
        return construirMensagemDeErro(
                businessException,
                mensagemErro,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
}