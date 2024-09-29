package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.impl;

import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.ListarAgendamentosPorQuadraUsecase;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.ListarAgendamentosPorUsuarioUsecase;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.*;
import com.pucminas.gestaoquadras.agendamento.quadra.Quadra;
import com.pucminas.gestaoquadras.agendamento.usuario.Usuario;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class ListarAgendamentosPorQuadraUsecaseImpl implements ListarAgendamentosPorQuadraUsecase {
    private final AgendamentoGateway agendamentoGateway;

    @Inject
    public ListarAgendamentosPorQuadraUsecaseImpl(AgendamentoGateway agendamentoGateway) {
        this.agendamentoGateway = agendamentoGateway;
    }

    @Override
    public ListarAgendamentosPorQuadraUsecaseOutput execute(ListarAgendamentosPorQuadraUsecaseInput input) {
        final var quadra = new Quadra(input.id(), null, null, null, null);
        final var outputs = this.agendamentoGateway
                .getAgendamentosByQuadra(quadra)
                .stream()
                .map(AgendamentosUsecasesOutput::fromDomain)
                .toList();
        return new ListarAgendamentosPorQuadraUsecaseOutput(outputs);
    }
}
