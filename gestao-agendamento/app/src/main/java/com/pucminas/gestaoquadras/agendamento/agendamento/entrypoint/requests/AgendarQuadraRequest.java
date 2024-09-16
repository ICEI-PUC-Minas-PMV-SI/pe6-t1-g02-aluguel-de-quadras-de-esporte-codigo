package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests;

import java.time.Instant;

public record AgendarQuadraRequest(
        String quadraId, String usuarioId, Instant inicioAgendamento, Instant fimAgendamento
) {
}
