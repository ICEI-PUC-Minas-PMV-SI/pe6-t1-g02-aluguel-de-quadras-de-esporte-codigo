package com.pucminas.gestaoquadras.usuarios.exceptions;

public class SenhaValidationException extends RuntimeException {
    public SenhaValidationException(String message) {
        super(message, null, true, false);
    }
}
