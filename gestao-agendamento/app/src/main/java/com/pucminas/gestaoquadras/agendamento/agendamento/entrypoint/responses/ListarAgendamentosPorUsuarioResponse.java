package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;

import java.util.Set;

public record ListarAgendamentosPorUsuarioResponse(
        Set<Agendamento> agendamentos
) {
}
