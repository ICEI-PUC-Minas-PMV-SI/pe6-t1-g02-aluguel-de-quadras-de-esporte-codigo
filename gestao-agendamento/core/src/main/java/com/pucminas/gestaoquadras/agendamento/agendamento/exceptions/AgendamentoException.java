package com.pucminas.gestaoquadras.agendamento.agendamento.exceptions;

public class AgendamentoException extends RuntimeException {
    public AgendamentoException(final String message) {
        super(message, null, true, false);
    }
}
