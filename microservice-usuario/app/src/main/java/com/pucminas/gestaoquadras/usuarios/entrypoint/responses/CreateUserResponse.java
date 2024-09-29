package com.pucminas.gestaoquadras.usuarios.entrypoint.responses;

import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseOutput;

import java.time.Instant;

public record CreateUserResponse (
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

    public static CreateUserResponse from(CreateUsuarioUseCaseOutput useCaseOutput) {
        return new CreateUserResponse(
                useCaseOutput.id(),
                useCaseOutput.cpf(),
                useCaseOutput.cnpj(),
                useCaseOutput.nome(),
                useCaseOutput.telefone(),
                useCaseOutput.email(),
                useCaseOutput.dataCriacao(),
                useCaseOutput.dataUltimaAtualizacao(),
                useCaseOutput.dataDelecao()
        );
    }
}
