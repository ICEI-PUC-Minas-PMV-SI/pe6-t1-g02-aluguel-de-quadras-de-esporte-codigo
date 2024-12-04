package com.pucminas.gestaoquadras.usuarios.entrypoint.requests;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotNull
        String nome,
        @NotNull
        String telefone,
        @NotNull
        String email,
        @NotNull
        String senha,
        @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
        String cpf,
        @Size(min = 14, max = 14, message = "CNPJ deve ter 14 caracteres")
        String cnpj
) {
}
