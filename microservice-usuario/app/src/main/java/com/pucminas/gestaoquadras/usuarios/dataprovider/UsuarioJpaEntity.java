package com.pucminas.gestaoquadras.usuarios.dataprovider;

import com.pucminas.gestaoquadras.usuarios.auth.LocadorGrant;
import com.pucminas.gestaoquadras.usuarios.auth.LocatarioGrant;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Email;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Senha;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class UsuarioJpaEntity implements UserDetails {
    @Id
    private String id;
    @Column(nullable = true)
    private String cnpj;
    @Column(nullable = true)
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String senha;
    private Instant dataCriacao;
    private Instant dataUltimaAtualizacao;
    private Instant dataDelecao;

    public UsuarioJpaEntity() {
    }

    public UsuarioJpaEntity(String id, String cnpj, String cpf, String nome, String telefone, String email, String senha, Instant dataCriacao, Instant dataUltimaAtualizacao, Instant dataDelecao) {
        this.id = id;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.dataDelecao = dataDelecao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Instant getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Instant dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public Instant getDataDelecao() {
        return dataDelecao;
    }

    public void setDataDelecao(Instant dataDelecao) {
        this.dataDelecao = dataDelecao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(cnpj != null) {
            return List.of(new LocadorGrant());
        } else {
            return List.of(new LocatarioGrant());
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
