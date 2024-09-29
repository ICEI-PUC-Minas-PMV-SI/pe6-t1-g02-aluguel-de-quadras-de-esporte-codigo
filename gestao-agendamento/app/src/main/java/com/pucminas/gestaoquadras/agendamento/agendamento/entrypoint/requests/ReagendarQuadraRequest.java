package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record ReagendarQuadraRequest (
        @NotNull
        Instant inicioAgendamento,
        @NotNull
        Instant fimAgendamento
) {
}
