package com.pucminas.gestaoquadras.usuarios.exceptions;

public class EmailValidationException extends RuntimeException {
    public EmailValidationException(String message) {
        super(message, null, true, false);
    }
}
