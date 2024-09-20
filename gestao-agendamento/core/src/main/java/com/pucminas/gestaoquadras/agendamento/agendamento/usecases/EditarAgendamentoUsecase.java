package com.pucminas.gestaoquadras.agendamento.agendamento.usecases;


import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.EditarAgendamentoUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.EditarAgendamentoUsecaseOutput;

public interface EditarAgendamentoUsecase {
    void execute(EditarAgendamentoUsecaseInput input);
}
