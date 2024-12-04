package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests;

import java.time.Instant;

public record AgendarQuadraRequest(
        String idQuadra, String idUsuario, Instant inicioAgendamento, Instant fimAgendamento
) {
}
