package com.pucminas.gestaoquadras.usuarios.dataprovider;

import com.pucminas.gestaoquadras.usuarios.entities.Usuario;
import com.pucminas.gestaoquadras.usuarios.entities.UsuarioFactory;

public class UsuarioMapper {

    public static Usuario toDomain(UsuarioJpaEntity jpaEntity) {
        return UsuarioFactory.of(
                jpaEntity.getId(),
                jpaEntity.getNome(),
                jpaEntity.getTelefone(),
                jpaEntity.getEmail(),
                jpaEntity.getCnpj(),
                jpaEntity.getCpf(),
                jpaEntity.getDataCriacao(),
                jpaEntity.getDataUltimaAtualizacao(),
                jpaEntity.getDataDelecao()
        );
    }

    public static UsuarioJpaEntity toJpa(Usuario usuario) {
        final var cnpj = usuario.getCnpj() == null? null : usuario.getCnpj().getValue();
        final var cpf = usuario.getCpf() == null? null : usuario.getCpf().getValue();

        return new UsuarioJpaEntity(
                usuario.getId(),
                cnpj,
                cpf,
                usuario.getNome(),
                usuario.getTelefone(),
                usuario.getEmail().getValue(),
                usuario.getSenha().getValue(),
                usuario.getDataCriacao(),
                usuario.getDataUltimaAtualizacao(),
                usuario.getDataDelecao()
        );
    }
}
