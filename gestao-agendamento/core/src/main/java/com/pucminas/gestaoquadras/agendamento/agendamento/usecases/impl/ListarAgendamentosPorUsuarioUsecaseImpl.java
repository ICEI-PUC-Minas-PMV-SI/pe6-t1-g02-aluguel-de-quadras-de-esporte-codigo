package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.impl;

import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.NotFoundException;
import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.ListarAgendamentosPorUsuarioUsecase;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.AgendamentosUsecasesOutput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseOutput;
import com.pucminas.gestaoquadras.agendamento.usuario.Usuario;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseOutput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.stream.Collectors;
import java.util.Objects;

@Named
public class ListarAgendamentosPorUsuarioUsecaseImpl implements ListarAgendamentosPorUsuarioUsecase {
    private final AgendamentoGateway agendamentoGateway;

    @Inject
    public ListarAgendamentosPorUsuarioUsecaseImpl(final AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = Objects.requireNonNull(agendamentoGateway);
    }

    @Override
    public ListarAgendamentosPorUsuarioUsecaseOutput execute(ListarAgendamentosPorUsuarioUsecaseInput input) {
        final var anUsuario = new Usuario(input.id(), null);
        final var outputs = this.agendamentoGateway
                .getAgendamentosByUsuario(anUsuario)
                .stream()
                .map(AgendamentosUsecasesOutput::fromDomain)
                .toList();
        return new ListarAgendamentosPorUsuarioUsecaseOutput(outputs);
    }
}
