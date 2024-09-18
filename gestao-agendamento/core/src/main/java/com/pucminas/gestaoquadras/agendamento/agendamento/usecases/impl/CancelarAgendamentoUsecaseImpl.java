package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.impl;

import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.AgendamentoException;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.CancelarAgendamentoUsecase;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.CancelarAgendamentoUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.CancelarAgendamentoUsecaseOutput;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoID;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoStatus;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class CancelarAgendamentoUsecaseImpl implements CancelarAgendamentoUsecase {

    private final AgendamentoGateway agendamentoGateway;

    @Inject
    public CancelarAgendamentoUsecaseImpl(final AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public void execute(CancelarAgendamentoUsecaseInput input) {
        final var agendamento = agendamentoGateway.getAgendamentoById(AgendamentoID.from(input.idAgendamento()));

        if (agendamento.getStatus() == AgendamentoStatus.CANCELADO) {
            throw new AgendamentoException("Esse agendamento já foi cancelado!");
        }

        agendamento.setStatus(AgendamentoStatus.CANCELADO);
        agendamentoGateway.save(agendamento);
    }
}
