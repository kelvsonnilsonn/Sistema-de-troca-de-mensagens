package com.orizon.system.message.exceptions;

public class InvalidMessageContentException extends RuntimeException {
    public InvalidMessageContentException() {
        super("A mensagem a ser enviada não deve ser nula ou vazia.");
    }
    public InvalidMessageContentException(String message) {
        super(message);
    }
}
