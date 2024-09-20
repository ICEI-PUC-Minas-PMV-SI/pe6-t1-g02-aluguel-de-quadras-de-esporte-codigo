package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.impl;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;
import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.NotFoundException;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.EditarAgendamentoUsecase;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.EditarAgendamentoUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.EditarAgendamentoUsecaseOutput;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoID;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoStatus;
import com.pucminas.gestaoquadras.agendamento.quadra.dataprovider.QuadraGateway;
import com.pucminas.gestaoquadras.agendamento.usuario.dataprovider.UsuarioGateway;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class EditarAgendamentoUsecaseImpl implements EditarAgendamentoUsecase {

    private final AgendamentoGateway agendamentoGateway;
//    private final QuadraGateway quadraGateway;

    @Inject
    public EditarAgendamentoUsecaseImpl(
            final AgendamentoGateway agendamentoGateway,
            final QuadraGateway quadraGateway) {
        this.agendamentoGateway = Objects.requireNonNull(agendamentoGateway);
//        this.quadraGateway = Objects.requireNonNull(quadraGateway);
    }
    @Override
    public void execute(EditarAgendamentoUsecaseInput input) {
        final var agendamento = agendamentoGateway.getAgendamentoById(AgendamentoID.from(input.agendamentoId()));

        if (Objects.isNull(agendamento)) {
            throw new NotFoundException("Agendamento não encontrado!");
        }

        agendamento.setInicioAgendamento(input.inicioAgendamento());
        agendamento.setFimAgendamento(input.fimAgendamento());

        agendamentoGateway.save(agendamento);
    }
}
