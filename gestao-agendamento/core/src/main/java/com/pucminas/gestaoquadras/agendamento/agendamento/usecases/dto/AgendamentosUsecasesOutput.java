package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;

import java.time.Instant;

public record AgendamentosUsecasesOutput(
        String idAgendamento,
        String idQuadra,
        String idUsuario,
        Instant dataInicio,
        Instant dataFim,
        String status
) {

    public static AgendamentosUsecasesOutput fromDomain(Agendamento agendamento) {
        return new AgendamentosUsecasesOutput(
                agendamento.getId().getValue(),
                agendamento.getQuadra().quadraId(),
                agendamento.getUsuario().id(),
                agendamento.getInicioAgendamento(),
                agendamento.getFimAgendamento(),
                agendamento.getStatus().name()
        );
    }
}
