package com.pucminas.gestaoquadras.usuarios.entrypoint.requests;

import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
        @NotNull
        String senha,
        @NotNull
        String telefone,
        @NotNull
        String nome
) {
}
