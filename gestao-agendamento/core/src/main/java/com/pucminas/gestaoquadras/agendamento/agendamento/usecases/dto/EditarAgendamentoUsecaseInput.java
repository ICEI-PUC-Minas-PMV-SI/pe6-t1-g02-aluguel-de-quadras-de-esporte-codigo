package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto;

import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoID;

import java.time.Instant;

public record EditarAgendamentoUsecaseInput(
        String agendamentoId,
//        String quadraId,
        Instant inicioAgendamento,
        Instant fimAgendamento

) {
}
