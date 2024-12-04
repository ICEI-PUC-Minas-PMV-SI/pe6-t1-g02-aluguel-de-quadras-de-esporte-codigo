package com.pucminas.gestaoquadras.agendamento.agendamento.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message, null, true, false);
    }
}
