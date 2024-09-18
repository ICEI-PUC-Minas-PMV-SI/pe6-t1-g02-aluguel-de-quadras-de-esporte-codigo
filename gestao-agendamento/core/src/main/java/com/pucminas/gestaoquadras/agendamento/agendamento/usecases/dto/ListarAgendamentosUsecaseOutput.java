package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;

import java.util.Set;

public record ListarAgendamentosUsecaseOutput(
        Set<Agendamento> agendamentos
) {
}
