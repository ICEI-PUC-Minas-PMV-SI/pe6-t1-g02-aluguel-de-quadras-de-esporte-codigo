package com.pucminas.gestaoquadras.usuarios.usecases.create.impl;

import com.pucminas.gestaoquadras.usuarios.dataprovider.UsuarioGateway;
import com.pucminas.gestaoquadras.usuarios.entities.UsuarioFactory;
import com.pucminas.gestaoquadras.usuarios.exceptions.EmailValidationException;
import com.pucminas.gestaoquadras.usuarios.usecases.create.CreateUsuarioUseCase;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseOutput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class CreateUsuarioUseCaseImpl implements CreateUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    @Inject
    public CreateUsuarioUseCaseImpl(
            final UsuarioGateway usuarioGateway
    ) {
        this.usuarioGateway = Objects.requireNonNull(usuarioGateway);
    }

    @Override
    public CreateUsuarioUseCaseOutput create(CreateUsuarioUseCaseInput input) {
        // Checa se usuario ja existe
        if (this.usuarioGateway.getUserByEmail(input.email().getValue()).isPresent()) {
            throw new EmailValidationException("Email já cadastrado");
        }
        final var usuario = UsuarioFactory.of(input.senha(), input.email(), input.telefone(), input.nome(), input.cpf(), input.cnpj());
        final var savedUsuario = this.usuarioGateway.save(usuario);

        return new CreateUsuarioUseCaseOutput(
                savedUsuario.getId(),
                savedUsuario.getCpf() == null? null : savedUsuario.getCpf().getValue(),
                savedUsuario.getCnpj() == null? null : savedUsuario.getCnpj().getValue(),
                savedUsuario.getNome(),
                savedUsuario.getTelefone(),
                savedUsuario.getEmail().getValue(),
                savedUsuario.getDataCriacao(),
                savedUsuario.getDataUltimaAtualizacao(),
                savedUsuario.getDataDelecao()
        );
    }
}
