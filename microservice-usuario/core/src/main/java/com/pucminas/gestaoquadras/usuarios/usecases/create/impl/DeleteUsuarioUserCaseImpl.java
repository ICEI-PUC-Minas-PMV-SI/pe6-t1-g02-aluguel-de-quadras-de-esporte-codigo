package com.pucminas.gestaoquadras.usuarios.usecases.create.impl;

import com.pucminas.gestaoquadras.usuarios.dataprovider.UsuarioGateway;
import com.pucminas.gestaoquadras.usuarios.usecases.create.DeleteUsuarioUseCase;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.DeleteUsuarioCaseInput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class DeleteUsuarioUserCaseImpl implements DeleteUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    @Inject
    public DeleteUsuarioUserCaseImpl(
            final UsuarioGateway usuarioGateway
    )
    {
        this.usuarioGateway = Objects.requireNonNull(usuarioGateway);
    }
    @Override
    public void delete(DeleteUsuarioCaseInput input) {
        this.usuarioGateway.deleteUser(input.id());
    }

}
