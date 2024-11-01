package com.pucminas.gestaoquadras.login.entrypoint.response;

import com.pucminas.gestaoquadras.usuarios.dataprovider.UsuarioJpaEntity;

public record UserDto(
        String id,
        String cpf,
        String cnpj,
        String nome,
        String email
) {

    public static UserDto from(UsuarioJpaEntity jpa) {
        return new UserDto(
                jpa.getId(),
                jpa.getCpf(),
                jpa.getCnpj(),
                jpa.getNome(),
                jpa.getEmail()
        );
    }
}
