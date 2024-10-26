package com.pucminas.gestaoquadras.login.entrypoint.response;

public record LoginResponse(
        String token,
        Long expiresIn,
        UserDto user
) {
}
