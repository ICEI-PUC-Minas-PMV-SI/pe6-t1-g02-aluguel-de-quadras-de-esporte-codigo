package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses;

import java.time.Instant;

public record AgendarQuadraResponse(
        String id,
        String status,
        String quadraId,
        String usuarioid,
        Instant inicioAgendamento,
        Instant fimAgendamento
) {
}
