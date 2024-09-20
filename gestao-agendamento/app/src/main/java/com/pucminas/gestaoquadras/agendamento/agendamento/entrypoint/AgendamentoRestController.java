package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.AgendarQuadraRequest;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.EditarAgendamentoRequest;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.ListarAgendamentosPorUsuarioRequest;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses.AgendarQuadraResponse;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses.ListarAgendamentosPorUsuarioResponse;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses.ListarAgendamentosResponse;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.*;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.AgendarQuadraUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.CancelarAgendamentoUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.EditarAgendamentoUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.usuario.Usuario;
import jakarta.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;
import java.util.Set;

@RestController
public class AgendamentoRestController implements AgendamentoRestEndpoint {
    private final AgendarQuadraUsecase agendarQuadraUsecase;
    private final CancelarAgendamentoUsecase cancelarAgendamentoUseCase;
    private final ListarAgendamentosPorUsuarioUsecase listarAgendamentosPorUsuarioUsecase;
    private final ListarAgendamentosUsecase listarAgendamentosUsecase;

    private final EditarAgendamentoUsecase editarAgendamentoUsecase;

    @Inject
    public AgendamentoRestController(
            final AgendarQuadraUsecase agendarQuadraUsecase,
            final CancelarAgendamentoUsecase cancelarAgendamentoUseCase,
            final ListarAgendamentosPorUsuarioUsecase listarAgendamentosPorUsuarioUsecase,
            final ListarAgendamentosUsecase listarAgendamentosUsecase,
            final EditarAgendamentoUsecase editarAgendamentoUsecase) {
        this.agendarQuadraUsecase = Objects.requireNonNull(agendarQuadraUsecase);
        this.cancelarAgendamentoUseCase = Objects.requireNonNull(cancelarAgendamentoUseCase);
        this.listarAgendamentosPorUsuarioUsecase = Objects.requireNonNull(listarAgendamentosPorUsuarioUsecase);
        this.listarAgendamentosUsecase = Objects.requireNonNull(listarAgendamentosUsecase);
        this.editarAgendamentoUsecase = Objects.requireNonNull(editarAgendamentoUsecase);
    }

    @Override
    public ResponseEntity<AgendarQuadraResponse> agendarQuadra(AgendarQuadraRequest request) {
        final var usecaseOutput = agendarQuadraUsecase.execute(
                new AgendarQuadraUsecaseInput(request.usuarioId(), request.quadraId(), request.inicioAgendamento(), request.fimAgendamento())
        );

        final var response = new AgendarQuadraResponse(
                usecaseOutput.id(),
                usecaseOutput.status(),
                usecaseOutput.quadra(),
                usecaseOutput.usuario(),
                usecaseOutput.inicioAgendamento(),
                usecaseOutput.fimAgendamento()
        );

        final var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<?> cancelarAgendamento(String id) {
        cancelarAgendamentoUseCase.execute(new CancelarAgendamentoUsecaseInput(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Set<Agendamento>> listarAgendamentosPorUsuario(ListarAgendamentosPorUsuarioRequest request) {
        final var usecaseOutput = listarAgendamentosPorUsuarioUsecase.execute(
                new ListarAgendamentosPorUsuarioUsecaseInput(
                        new Usuario(request.id(), null)
                )
        );

        final var response = new ListarAgendamentosPorUsuarioResponse(
                usecaseOutput.agendamentos()
        );

        return ResponseEntity.ok(response.agendamentos());
    }

    @Override
    public ResponseEntity<Set<Agendamento>> listarAgendamentos() {
        final var usecaseOutput = listarAgendamentosUsecase.execute();

        final var response = new ListarAgendamentosResponse(usecaseOutput.agendamentos());

        return ResponseEntity.ok(response.agendamentos());
    }

    @Override
    public ResponseEntity<?> editarAgendamento(EditarAgendamentoRequest request) {
        editarAgendamentoUsecase.execute(
                new EditarAgendamentoUsecaseInput(
                        request.agendamentoId(),
                        request.inicioAgendamento(),
                        request.fimAgendamento()
                )
        );
        return ResponseEntity.noContent().build();
    }
}
