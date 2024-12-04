package com.pucminas.gestaoquadras.usuarios.entities;

import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CNPJ;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CPF;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Email;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Senha;

import java.time.Instant;
import java.util.Objects;

public class UsuarioFactory {

    public static Usuario of(
            final String id,
            final String nome,
            final String telefone,
            final String email,
            final String cnpj,
            final String cpf,
            final Instant dataCriacao,
            final Instant dataUltimaAtualizacao,
            final Instant dataDelecao
    ) {
        final var anEmail = Email.of(email);
        final var aCnpj = cnpj == null ? null : CNPJ.of(cnpj);
        final var aCpf = cpf == null ? null : CPF.of(cpf);

        if (aCnpj != null) {
            return Locador.of(id, aCnpj, anEmail, telefone, nome, dataCriacao, dataUltimaAtualizacao, dataDelecao);
        } else {
            return Locatario.of(id, aCpf, anEmail, telefone, nome, dataCriacao, dataUltimaAtualizacao, dataDelecao);
        }
    }

    public static Usuario of(
            final Senha senha,
            final Email email,
            final String telefone,
            final String nome,
            final CPF cpf,
            final CNPJ cnpj
    ) {
        if (Objects.isNull(cpf.getValue()) && Objects.isNull(cnpj.getValue())) {
            throw new IllegalArgumentException("CPF ou CNPJ deve ser informados");
        }

        if(cpf.getValue() != null && cpf.getValue().length() == 11) {
            return Locatario.newLocatario(cpf, senha, email, telefone, nome);
        }
        if(cnpj.getValue() != null && cnpj.getValue().length() == 14) {
            return Locador.newLocador(cnpj, senha, email, telefone, nome);
        }

        throw new IllegalArgumentException("CPF ou CNPJ inválidos");
    }

    public static Usuario of(
            final Senha senha,
            final Email email,
            final String telefone,
            final String nome,
            final CNPJ cnpj
    ) {
        return Locador.newLocador(cnpj, senha, email, telefone, nome);
    }

    public static Usuario of(
            final Senha senha,
            final Email email,
            final String telefone,
            final String nome,
            final CPF cpf
    ) {
        return Locatario.newLocatario(cpf, senha, email, telefone, nome);
    }
}
