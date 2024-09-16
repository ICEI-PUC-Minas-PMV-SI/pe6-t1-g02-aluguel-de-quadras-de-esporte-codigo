package com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "agendamentos")
public class AgendamentoJpaEntity {
    @Id
    private String id;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "quadra", nullable = false)
    private String quadra;
    @Column(name = "usuario", nullable = false)
    private String usuario;
    @Column(name = "inicioAgendamento", nullable = false)
    private Instant inicioAgendamento;
    @Column(name = "fimAgendamento", nullable = false)
    private Instant fimAgendamento;

    public AgendamentoJpaEntity(String id, String status, String quadra, String usuario, Instant inicioAgendamento, Instant fimAgendamento) {
        this.id = id;
        this.status = status;
        this.quadra = quadra;
        this.usuario = usuario;
        this.inicioAgendamento = inicioAgendamento;
        this.fimAgendamento = fimAgendamento;
    }

    public AgendamentoJpaEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuadra() {
        return quadra;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Instant getInicioAgendamento() {
        return inicioAgendamento;
    }

    public void setInicioAgendamento(Instant inicioAgendamento) {
        this.inicioAgendamento = inicioAgendamento;
    }

    public Instant getFimAgendamento() {
        return fimAgendamento;
    }

    public void setFimAgendamento(Instant fimAgendamento) {
        this.fimAgendamento = fimAgendamento;
    }
}
