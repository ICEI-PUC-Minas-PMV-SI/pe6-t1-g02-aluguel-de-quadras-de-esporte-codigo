package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;

import java.time.Instant;

public record EditarAgendamentoResponse(
        Agendamento agendamento
) {
}
