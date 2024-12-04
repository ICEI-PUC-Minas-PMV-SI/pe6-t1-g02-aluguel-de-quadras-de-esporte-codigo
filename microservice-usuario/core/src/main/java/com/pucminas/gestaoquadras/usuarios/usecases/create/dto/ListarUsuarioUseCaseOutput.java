package com.pucminas.gestaoquadras.usuarios.usecases.create.dto;

import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Email;

import java.time.Instant;
import java.util.List;

public record ListarUsuarioUseCaseOutput(
        List<GetUsuarioUseCaseOutput> usuario
) {
}
