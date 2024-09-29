package com.pucminas.gestaoquadras.usuarios.usecases.create.dto;

import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Senha;

public record UpdateUsuarioCaseInput(
        String usuarioId,
        Senha senha,
        String telefone,
        String nome
) {

}
