package com.pucminas.gestaoquadras.usuarios.usecases.create.dto;

import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Email;

import java.time.Instant;

public record CreateUsuarioUseCaseOutput(
        String id,
        String cpf,
        String cnpj,
        String nome,
        String telefone,
        String email,
        Instant dataCriacao,
        Instant dataUltimaAtualizacao,
        Instant dataDelecao
) {
}
