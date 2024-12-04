package com.pucminas.gestaoquadras.login.entrypoint.request;

public record LoginRequest(
        String email,
        String senha
) {
}
