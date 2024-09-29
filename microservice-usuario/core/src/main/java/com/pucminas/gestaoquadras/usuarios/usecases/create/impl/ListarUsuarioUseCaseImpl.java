package com.pucminas.gestaoquadras.usuarios.usecases.create.impl;

import com.pucminas.gestaoquadras.usuarios.dataprovider.UsuarioGateway;
import com.pucminas.gestaoquadras.usuarios.usecases.create.ListarUsuarioUseCase;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.GetUsuarioUseCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.GetUsuarioUseCaseOutput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.ListarUsuarioUseCaseOutput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class ListarUsuarioUseCaseImpl implements ListarUsuarioUseCase
{
    private  final UsuarioGateway usuarioGateway;

    @Inject
    public ListarUsuarioUseCaseImpl(
            UsuarioGateway usuarioGateway
    ) {
        {
            this.usuarioGateway = Objects.requireNonNull(usuarioGateway);
        }

    }

    @Override
    public ListarUsuarioUseCaseOutput listar() {
       final var output= this.usuarioGateway.
                listUser().stream().map(usuario -> {
                    return new GetUsuarioUseCaseOutput(
                            usuario .getId(),
                            usuario.getCpf().getValue(),
                            usuario.getCnpj().getValue(),
                            usuario.getNome(),
                            usuario.getTelefone(),
                            usuario.getEmail().getValue(),
                            usuario.getDataCriacao(),
                            usuario.getDataUltimaAtualizacao(),
                            usuario.getDataDelecao()
                    );

                }).toList();
       return new ListarUsuarioUseCaseOutput(output);
    }
}
