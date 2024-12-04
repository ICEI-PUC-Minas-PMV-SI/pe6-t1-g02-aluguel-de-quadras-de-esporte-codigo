package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;

import java.time.Instant;

public record AgendamentosUsecasesOutput(
        String idAgendamento,
        String idQuadra,
        String idUsuario,
        Instant inicioAgendamento,
        Instant fimAgendamento,
        String status
) {

    public static AgendamentosUsecasesOutput fromDomain(Agendamento agendamento) {
        return new AgendamentosUsecasesOutput(
                agendamento.getId().getValue(),
                agendamento.getQuadra(),
                agendamento.getUsuario().id(),
                agendamento.getInicioAgendamento(),
                agendamento.getFimAgendamento(),
                agendamento.getStatus().name()
        );
    }
}
