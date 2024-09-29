package com.pucminas.gestaoquadras.agendamento.configuration.auth;

import java.util.List;

public record User(
        String email,
        String id,
        List<String> authorities
) {
}
