package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.impl;

import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.ListarAgendamentosPorUsuarioUsecase;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseOutput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

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
        final var agendamentos = agendamentoGateway.getAgendamentosByUsuario(input.usuario());
        return new ListarAgendamentosPorUsuarioUsecaseOutput(agendamentos);
    }
}
