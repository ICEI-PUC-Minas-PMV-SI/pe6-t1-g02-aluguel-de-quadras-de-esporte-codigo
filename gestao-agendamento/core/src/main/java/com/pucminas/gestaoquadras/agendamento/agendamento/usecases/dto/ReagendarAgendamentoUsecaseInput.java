package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto;

import java.time.Instant;

public record ReagendarAgendamentoUsecaseInput(
        String idAgendamento,
        Instant dataInicio,
        Instant dataFim
){
}
