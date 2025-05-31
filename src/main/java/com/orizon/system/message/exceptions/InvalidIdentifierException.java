package com.orizon.system.message.exceptions;

public class InvalidIdentifierException extends RuntimeException {
    public InvalidIdentifierException() {super("Você inseriu um id inválido.");}
    public InvalidIdentifierException(String message) {
        super(message);
    }
}
