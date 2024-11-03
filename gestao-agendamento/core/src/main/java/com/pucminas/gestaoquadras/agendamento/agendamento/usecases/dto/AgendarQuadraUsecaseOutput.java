package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;

import java.time.Instant;

public record AgendarQuadraUsecaseOutput (
        String id,
        String status,
        String quadra,
        String usuario,
        Instant inicioAgendamento,
        Instant fimAgendamento
){

    public static AgendarQuadraUsecaseOutput from(Agendamento agendamento) {
        return new AgendarQuadraUsecaseOutput(
                agendamento.getId().getValue(),
                agendamento.getStatus().name(),
                agendamento.getQuadra(),
                agendamento.getUsuario().id(),
                agendamento.getInicioAgendamento(),
                agendamento.getFimAgendamento()
        );
    }
}
