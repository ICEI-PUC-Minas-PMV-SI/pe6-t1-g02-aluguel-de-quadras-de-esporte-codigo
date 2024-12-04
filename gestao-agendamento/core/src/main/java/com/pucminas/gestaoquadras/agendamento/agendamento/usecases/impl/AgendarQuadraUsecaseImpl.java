package com.pucminas.gestaoquadras.agendamento.agendamento.usecases.impl;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;
import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.AgendamentoException;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.NotFoundException;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.AgendarQuadraUsecase;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.AgendarQuadraUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.AgendarQuadraUsecaseOutput;
import com.pucminas.gestaoquadras.agendamento.quadra.dataprovider.QuadraGateway;
import com.pucminas.gestaoquadras.agendamento.usuario.dataprovider.UsuarioGateway;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Objects;

@Named
public class AgendarQuadraUsecaseImpl implements AgendarQuadraUsecase {

    //gateway: buscar informações fora do projeto
    //quadra        -> api
    //agendamento   -> banco de dados
    // usuario      -> api
    private final QuadraGateway quadraGateway;
    private final UsuarioGateway usuarioGateway;
    private final AgendamentoGateway agendamentoGateway;

    @Inject
    public AgendarQuadraUsecaseImpl(final QuadraGateway quadraGateway, final UsuarioGateway usuarioGateway, final AgendamentoGateway agendamentoGateway) {
        this.quadraGateway = Objects.requireNonNull(quadraGateway);
        this.usuarioGateway = Objects.requireNonNull(usuarioGateway);
        this.agendamentoGateway = Objects.requireNonNull(agendamentoGateway);
    }

    @Override
    public AgendarQuadraUsecaseOutput execute(AgendarQuadraUsecaseInput input) {
//        final var quadra = quadraGateway.getQuadraById(input.idQuadra());
//        if(Objects.isNull(quadra)) {
//            throw new NotFoundException("A quadra informada nao foi encontrada.");
//        }

        final var usuario = usuarioGateway.getUsuarioById(input.idUsuario());
        if(Objects.isNull(usuario)) {
            throw new NotFoundException("O usuario informado nao foi encontrado.");
        }
        if(Objects.isNull(usuario.cpf())) {
            throw new AgendamentoException("O usuario informado nao e pessoa fisica.");
        }

        final var agendamentosExistentes = agendamentoGateway.getCountAgendamentosByQuadraEHorario(input.idQuadra(), input.inicioAgendamento(), input.fimAgendamento());

        if(!agendamentosExistentes.isEmpty()) {
            throw new AgendamentoException("A quadra ja esta agendada no horario desejado.");
        }

        final var agendamento = Agendamento.newAgendamento(input.idQuadra(), usuario, input.inicioAgendamento(), input.fimAgendamento());

        return AgendarQuadraUsecaseOutput.from(agendamentoGateway.save(agendamento));
    }
}
