package com.pucminas.gestaoquadras.usuarios.usecases.create.impl;

import com.pucminas.gestaoquadras.usuarios.dataprovider.UsuarioGateway;
import com.pucminas.gestaoquadras.usuarios.entities.Usuario;
import com.pucminas.gestaoquadras.usuarios.usecases.create.GetUsuarioUseCase;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseOutput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.GetUsuarioUseCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.GetUsuarioUseCaseOutput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;
import java.util.UUID;
@Named
public class GetUsuarioUseCaseImpl implements GetUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

@Inject
    public GetUsuarioUseCaseImpl(
            final UsuarioGateway usuarioGateway
    )
    {
        this.usuarioGateway = Objects.requireNonNull(usuarioGateway);
    }

@Override
    public GetUsuarioUseCaseOutput get(GetUsuarioUseCaseInput input){
        final var savedUsuario = this.usuarioGateway.getUser(input.id());

        return new GetUsuarioUseCaseOutput(
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
