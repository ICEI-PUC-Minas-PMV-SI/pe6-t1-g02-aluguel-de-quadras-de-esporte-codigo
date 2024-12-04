package com.pucminas.gestaoquadras.agendamento.agendamento.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String message) {
        super(message, null, true, false);
    }
}
