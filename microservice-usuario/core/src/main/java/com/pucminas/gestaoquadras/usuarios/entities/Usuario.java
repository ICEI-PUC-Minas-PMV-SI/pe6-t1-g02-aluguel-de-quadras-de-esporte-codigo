package com.pucminas.gestaoquadras.usuarios.entities;

import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CNPJ;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CPF;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Email;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Senha;

import java.time.Instant;
import java.util.UUID;

public abstract class Usuario {
    private String id;
    private String nome;
    private String telefone;
    private Email email;
    private Senha senha;
    private Instant dataCriacao;
    private Instant dataUltimaAtualizacao;
    private Instant dataDelecao;

    protected Usuario(String id, String nome, String telefone, Email email, Instant dataCriacao, Instant dataUltimaAtualizacao, Instant dataDelecao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.dataDelecao = dataDelecao;
    }

    protected Usuario(
            final Senha senha,
            final Email email,
            final String telefone,
            final String nome
    ) {
        this.id = UUID.randomUUID().toString();
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.dataCriacao = Instant.now();
        this.dataUltimaAtualizacao = Instant.now();
        this.dataDelecao = null;
    }


    protected void validate() {
    }

    public abstract CPF getCpf();

    public abstract CNPJ getCnpj();

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public Email getEmail() {
        return email;
    }

    public Senha getSenha() {
        return senha;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public Instant getDataDelecao() {
        return dataDelecao;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setSenha(Senha senha) {
        this.senha = senha;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
