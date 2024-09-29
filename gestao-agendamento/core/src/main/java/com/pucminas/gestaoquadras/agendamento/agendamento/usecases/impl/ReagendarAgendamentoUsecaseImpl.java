package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.impl;

import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.AgendamentoException;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.ReagendarAgendamentoUsecase;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ReagendarAgendamentoUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoID;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class ReagendarAgendamentoUsecaseImpl implements ReagendarAgendamentoUsecase {
    private final AgendamentoGateway agendamentoGateway;

    @Inject
    public ReagendarAgendamentoUsecaseImpl(final AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = Objects.requireNonNull(agendamentoGateway);
    }

    @Override
    public void execute(ReagendarAgendamentoUsecaseInput input) {
        final var savedAgendamento = this.agendamentoGateway.getAgendamentoById(AgendamentoID.from(input.idAgendamento()));
        savedAgendamento.reagendar(input.dataInicio(), input.dataFim());

        final var agendamentosExistentes = agendamentoGateway.getCountAgendamentosByQuadraEHorario(savedAgendamento.getQuadra(), input.dataInicio(), input.dataFim());
        agendamentosExistentes.remove(savedAgendamento.getId().getValue());

        if(!agendamentosExistentes.isEmpty()) {
            throw new AgendamentoException("A quadra ja esta agendada no horario desejado.");
        }

        this.agendamentoGateway.save(savedAgendamento);
    }
}
