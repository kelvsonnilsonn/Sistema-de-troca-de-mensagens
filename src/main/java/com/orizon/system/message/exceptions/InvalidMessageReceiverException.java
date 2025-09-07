package com.orizon.system.message.exceptions;

public class InvalidMessageReceiverException extends RuntimeException {
    public InvalidMessageReceiverException() {
        super("Você não é um receptor válido para esta mensagem.");
    }
    public InvalidMessageReceiverException(String message) {
        super(message);
    }
}
