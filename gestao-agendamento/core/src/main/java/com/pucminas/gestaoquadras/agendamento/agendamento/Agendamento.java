package com.pucminas.gestaoquadras.agendamento.agendamento;

import com.pucminas.gestaoquadras.agendamento.Entity;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.AgendamentoException;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoID;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoStatus;
import com.pucminas.gestaoquadras.agendamento.quadra.Quadra;
import com.pucminas.gestaoquadras.agendamento.usuario.Usuario;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Agendamento extends Entity<AgendamentoID> {
    private AgendamentoStatus status;
    private final String quadra;
    private final Usuario usuario;
    private Instant inicioAgendamento;
    private Instant fimAgendamento;

    private Agendamento(AgendamentoID id, AgendamentoStatus status, String quadra, Usuario usuario, Instant inicioAgendamento, Instant fimAgendamento) {
        this.id = id;
        this.status = status;
        this.quadra = quadra;
        this.usuario = usuario;
        this.inicioAgendamento = inicioAgendamento;
        this.fimAgendamento = fimAgendamento;
    }

    public static Agendamento newAgendamento(String quadra, Usuario usuario, Instant inicioAgendamento, Instant fimAgendamento) {
        final var agendamento = new Agendamento(AgendamentoID.unique(), AgendamentoStatus.AGENDADO, quadra, usuario, inicioAgendamento, fimAgendamento);
        agendamento.validate();
        return agendamento;
    }

    public static Agendamento with(String id, String status, String quadraId, String usuarioid, Instant inicioAgendamento, Instant fimAgendamento) {
        final var agendamento = new Agendamento(
                AgendamentoID.from(id),
                AgendamentoStatus.valueOf(status),
                quadraId,
                new Usuario(usuarioid, null),
                inicioAgendamento,
                fimAgendamento
        );
        agendamento.validate();
        return agendamento;
    }

    @Override
    public void validate() {

        if (Objects.isNull(this.id)) {
            throw new AgendamentoException("O agendamento deve ter um id nao nulo.");
        }

        if (Objects.isNull(this.fimAgendamento) || Objects.isNull(this.inicioAgendamento)) {
            throw new AgendamentoException("O agendamento deve ter data e hora de inicio e fim definidas.");
        }

        if (this.inicioAgendamento.isAfter(this.fimAgendamento)) {
            throw new AgendamentoException("O inicio do agendamento deve ser antes do fim.");
        }

        if (Objects.isNull(this.usuario)) {
            throw new AgendamentoException("O agendamento deve ter um usuario nao nulo.");
        }

        if (this.fimAgendamento.minus(30, ChronoUnit.MINUTES).isBefore(this.inicioAgendamento)) {
            throw new AgendamentoException("O tempo minimo de agendamento e de 30 minutos.");
        }

        if (Objects.isNull(this.quadra)) {
            throw new AgendamentoException("O agendamento deve ter uma quadra nao nula.");
        }
    }

    public void reagendar(Instant inicioAgendamento, Instant fimAgendamento) {
        this.inicioAgendamento = inicioAgendamento;
        this.fimAgendamento = fimAgendamento;
        this.validate();
    }

    public AgendamentoStatus getStatus() {
        return status;
    }

    public void setStatus(AgendamentoStatus status) {
        this.status = status;
    }

    public String getQuadra() {
        return quadra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Instant getInicioAgendamento() {
        return inicioAgendamento;
    }

    public Instant getFimAgendamento() {
        return fimAgendamento;
    }
}
