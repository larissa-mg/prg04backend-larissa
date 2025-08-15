package br.com.ifba.infrastructure.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException {

    // Identificador de versão para garantir a compatibilidade de serialização.
    @Serial
    private static final long serialVersionUID = 1L;

    // Construtor padrão sem argumentos.
    public BusinessException() {
        super();
    }

    // Construtor que recebe a mensagem de erro.
    public BusinessException(final String message) {
        super(message);
    }

    // Construtor que recebe a causa raiz da exceção.
    public BusinessException(final Throwable cause) {
        super(cause);
    }

    // Construtor que recebe a mensagem de erro e a causa raiz.
    public BusinessException(final String message, final Throwable cause) {
        super(message, cause);
    }
}