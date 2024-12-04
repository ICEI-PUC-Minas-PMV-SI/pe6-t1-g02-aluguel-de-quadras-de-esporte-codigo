package com.pucminas.gestaoquadras.usuarios.usecases.create.impl;

import com.pucminas.gestaoquadras.usuarios.dataprovider.UsuarioGateway;
import com.pucminas.gestaoquadras.usuarios.entities.Usuario;
import com.pucminas.gestaoquadras.usuarios.usecases.create.UpdateUsuarioUseCase;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.UpdateUsuarioCaseInput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class UpdateUsuarioUseCaseImpl implements UpdateUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    @Inject
    public UpdateUsuarioUseCaseImpl(
            final UsuarioGateway usuarioGateway) {
        this.usuarioGateway = Objects.requireNonNull(usuarioGateway);
    }

    @Override
    public void update(UpdateUsuarioCaseInput input) {
        final var usuario = usuarioGateway.getUser(input.usuarioId());

        if (Objects.nonNull(usuario)) {
            usuario.setNome(input.nome());
            usuario.setTelefone(input.telefone());
            usuario.setSenha(input.senha());

            usuarioGateway.save(usuario);
        } else {
            throw new RuntimeException("Usuario não encontrado");
        }
    }
}
