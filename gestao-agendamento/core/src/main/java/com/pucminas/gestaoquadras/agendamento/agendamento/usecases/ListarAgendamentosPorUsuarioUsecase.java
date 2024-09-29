package com.pucminas.gestaoquadras.agendamento.agendamento.usecases;

import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseOutput;

public interface ListarAgendamentosPorUsuarioUsecase {

    ListarAgendamentosPorUsuarioUsecaseOutput execute(ListarAgendamentosPorUsuarioUsecaseInput input);
}
