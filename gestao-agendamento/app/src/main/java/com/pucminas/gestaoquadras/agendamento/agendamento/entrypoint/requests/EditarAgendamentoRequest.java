package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests;

import java.time.Instant;

public record EditarAgendamentoRequest(
        String agendamentoId,
        String quadraId,
        Instant inicioAgendamento,
        Instant fimAgendamento
) {
}