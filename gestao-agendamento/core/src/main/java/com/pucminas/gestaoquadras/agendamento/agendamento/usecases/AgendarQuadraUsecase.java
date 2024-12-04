package com.pucminas.gestaoquadras.agendamento.agendamento.usecases;

import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.AgendarQuadraUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.AgendarQuadraUsecaseOutput;

public interface AgendarQuadraUsecase {

    AgendarQuadraUsecaseOutput execute(AgendarQuadraUsecaseInput input);
}
