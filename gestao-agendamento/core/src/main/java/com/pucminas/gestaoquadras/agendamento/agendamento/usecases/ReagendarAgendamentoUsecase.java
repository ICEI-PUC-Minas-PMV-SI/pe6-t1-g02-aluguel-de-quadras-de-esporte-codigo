package com.pucminas.gestaoquadras.agendamento.agendamento.usecases;

import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseOutput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ReagendarAgendamentoUsecaseInput;

public interface ReagendarAgendamentoUsecase {

    void execute(ReagendarAgendamentoUsecaseInput input);
}
