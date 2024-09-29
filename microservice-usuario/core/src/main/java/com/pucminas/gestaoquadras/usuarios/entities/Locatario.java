package com.pucminas.gestaoquadras.usuarios.entities;

import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CNPJ;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CPF;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Email;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Senha;

import java.time.Instant;
import java.util.Objects;

public class Locatario extends Usuario {
    private CPF cpf;

    private Locatario(
            final CPF cpf,
            final Senha senha,
            final Email email,
            final String telefone,
            final String nome
    ) {
        super(senha, email, telefone, nome);
        this.cpf = cpf;
    }

    public static Locatario newLocatario(
            final CPF cpf,
            final Senha senha,
            final Email email,
            final String telefone,
            final String nome
    ) {
        final var locatario = new Locatario(cpf, senha, email, telefone, nome);
        locatario.validate();
        return locatario;
    }

    private Locatario(
            final String id,
            final CPF cpf,
            final Email email,
            final String telefone,
            final String nome,
            Instant dataCriacao,
            Instant dataUltimaAtualizacao,
            Instant dataDelecao
    ) {
        super(id, nome, telefone, email, dataCriacao, dataUltimaAtualizacao, dataDelecao);
        this.cpf = cpf;
    }

    public static Locatario of(
            final String id,
            final CPF cpf,
            final Email email,
            final String telefone,
            final String nome,
            Instant dataCriacao,
            Instant dataUltimaAtualizacao,
            Instant dataDelecao
    ) {
        final var locatario = new Locatario(id, cpf, email, telefone, nome, dataCriacao, dataUltimaAtualizacao, dataDelecao);
        locatario.validate();
        return locatario;
    }


    protected void validate() {
        if (Objects.isNull(cpf)) {
            throw new IllegalArgumentException("CPF não pode ser nulo");
        }
        super.validate();
    }

    @Override
    public CPF getCpf() {
        return this.cpf;
    }

    @Override
    public CNPJ getCnpj() {
        return null;
    }
}
