package com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.AgendamentoException;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.NotFoundException;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoID;
import com.pucminas.gestaoquadras.agendamento.quadra.Quadra;
import com.pucminas.gestaoquadras.agendamento.usuario.Usuario;
import jakarta.inject.Inject;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AgendamentoGatewayImpl implements AgendamentoGateway {
    private final AgendamentoMySQLRepository repository;

    @Inject
    public AgendamentoGatewayImpl(final AgendamentoMySQLRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public Agendamento save(Agendamento agendamento) {
        final var jpa = AgendamentoMapper.toJpa(agendamento);
        final var savedJpa = repository.save(jpa);
        return AgendamentoMapper.toDomain(savedJpa);
    }

    @Override
    public Set<Agendamento> getAgendamentosByUsuario(Usuario usuario) {
        final var agendamentosJpa = repository.getAgendamentosByUsuario(usuario.id());
        return agendamentosJpa
                .stream()
                .map(AgendamentoMapper::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Integer getCountAgendamentosByQuadraEHorario(Quadra quadra, Instant inicioAgendamento, Instant fimAgendamento) {
        return 0;
    }

    @Override
    public Agendamento getAgendamentoById(AgendamentoID agendamentoID) {
        final var agendamento = repository.findById(agendamentoID.getValue());
        if (agendamento.isPresent()) {
            return AgendamentoMapper.toDomain(agendamento.get());
        } else {
            throw new NotFoundException("Agendamento não encontrado!");
        }
    }
}
