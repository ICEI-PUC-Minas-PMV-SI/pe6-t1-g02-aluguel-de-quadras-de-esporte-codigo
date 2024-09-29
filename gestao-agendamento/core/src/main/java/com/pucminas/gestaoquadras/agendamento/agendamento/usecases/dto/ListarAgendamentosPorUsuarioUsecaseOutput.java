package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto;

import java.util.List;

public record ListarAgendamentosPorUsuarioUsecaseOutput(
        List<AgendamentosUsecasesOutput> agendamentos
){
}
