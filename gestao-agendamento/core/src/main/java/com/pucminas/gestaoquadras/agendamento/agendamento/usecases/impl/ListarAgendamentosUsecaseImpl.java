package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.impl;

import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.NotFoundException;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.ListarAgendamentosUsecase;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosUsecaseOutput;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class ListarAgendamentosUsecaseImpl implements ListarAgendamentosUsecase {

    private final AgendamentoGateway agendamentoGateway;

    @Inject
    public ListarAgendamentosUsecaseImpl(final AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = Objects.requireNonNull(agendamentoGateway);
    }

    @Override
    public ListarAgendamentosUsecaseOutput execute() {
        final var agendamentos = agendamentoGateway.getAgendamentos();

        if (Objects.isNull(agendamentos) || agendamentos.isEmpty()) {
            throw new NotFoundException("Nenhum agendamento registrado!");
        }

        return new ListarAgendamentosUsecaseOutput(agendamentos);
    }
}
