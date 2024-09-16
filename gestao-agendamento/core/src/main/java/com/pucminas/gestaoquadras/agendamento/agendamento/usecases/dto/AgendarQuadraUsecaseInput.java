package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto;

import java.time.Instant;

public record AgendarQuadraUsecaseInput(
        String idUsuario,
        String idQuadra,
        Instant inicioAgendamento,
        Instant fimAgendamento
) {
}
