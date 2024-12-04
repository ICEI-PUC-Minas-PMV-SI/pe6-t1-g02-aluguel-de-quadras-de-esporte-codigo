package com.pucminas.gestaoquadras.usuarios.entities;

import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CNPJ;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CPF;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Email;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Senha;

import java.time.Instant;

public class Locador extends Usuario {
    private CNPJ cnpj;

    private Locador(final CNPJ cnpj,
                    final Senha senha,
                    final Email email,
                    final String telefone,
                    final String nome) {
        super(senha, email, telefone, nome);
        this.cnpj = cnpj;
    }

    public static Locador newLocador(
            final CNPJ cnpj,
            final Senha senha,
            final Email email,
            final String telefone,
            final String nome
    ) {
        final var locador = new Locador(cnpj, senha, email, telefone, nome);
        locador.validate();
        return locador;
    }

    private Locador(
            final String id,
            final CNPJ cnpj,
            final Email email,
            final String telefone,
            final String nome,
            Instant dataCriacao,
            Instant dataUltimaAtualizacao,
            Instant dataDelecao
    ) {
        super(id, nome, telefone, email, dataCriacao, dataUltimaAtualizacao, dataDelecao);
        this.cnpj = cnpj;
    }

    public static Locador of(
            final String id,
            final CNPJ cnpj,
            final Email email,
            final String telefone,
            final String nome,
            Instant dataCriacao,
            Instant dataUltimaAtualizacao,
            Instant dataDelecao
    ) {
        final var locador = new Locador(id, cnpj, email, telefone, nome, dataCriacao, dataUltimaAtualizacao, dataDelecao);
        locador.validate();
        return locador;
    }

    protected void validate() {
        if (cnpj == null) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo");
        }
        super.validate();
    }

    @Override
    public CPF getCpf() {
        return null;
    }

    @Override
    public CNPJ getCnpj() {
        return this.cnpj;
    }
}
