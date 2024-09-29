package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint;

import com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider.AgendamentoGateway;
import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.AgendarQuadraRequest;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.ReagendarQuadraRequest;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses.AgendarQuadraResponse;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.ForbiddenException;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.*;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.*;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoID;
import com.pucminas.gestaoquadras.agendamento.configuration.auth.User;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses.ListarAgendamentosResponse;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.AgendarQuadraUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.CancelarAgendamentoUsecaseInput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseInput;
import jakarta.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;
import java.util.Set;

@RestController
public class AgendamentoRestController implements AgendamentoRestEndpoint {
    private final AgendarQuadraUsecase agendarQuadraUsecase;
    private final CancelarAgendamentoUsecase cancelarAgendamentoUseCase;
    private final ReagendarAgendamentoUsecase reagendarAgendamentoUsecase;
    private final ListarAgendamentosPorQuadraUsecase listarAgendamentosPorQuadraUsecase;
    private final ListarAgendamentosPorUsuarioUsecase listarAgendamentosPorUsuarioUsecase;
    private final AgendamentoGateway agendamentoGateway;
    private final ListarAgendamentosUsecase listarAgendamentosUsecase;

    @Inject
    public AgendamentoRestController(
            final AgendarQuadraUsecase agendarQuadraUsecase,
            final CancelarAgendamentoUsecase cancelarAgendamentoUseCase,
            final ReagendarAgendamentoUsecase reagendarAgendamentoUsecase,
            final ListarAgendamentosPorQuadraUsecase listarAgendamentosPorQuadraUsecase,
            final AgendamentoGateway agendamentoGateway,
            final ListarAgendamentosPorUsuarioUsecase listarAgendamentosPorUsuarioUsecase,
            final ListarAgendamentosUsecase listarAgendamentosUsecase
    ) {
        this.agendarQuadraUsecase = Objects.requireNonNull(agendarQuadraUsecase);
        this.cancelarAgendamentoUseCase = Objects.requireNonNull(cancelarAgendamentoUseCase);
        this.reagendarAgendamentoUsecase = Objects.requireNonNull(reagendarAgendamentoUsecase);
        this.listarAgendamentosPorQuadraUsecase = Objects.requireNonNull(listarAgendamentosPorQuadraUsecase);
        this.listarAgendamentosPorUsuarioUsecase = Objects.requireNonNull(listarAgendamentosPorUsuarioUsecase);
        this.agendamentoGateway = Objects.requireNonNull(agendamentoGateway);
        this.listarAgendamentosUsecase = Objects.requireNonNull(listarAgendamentosUsecase);
    }

    @Override
    public ResponseEntity<AgendarQuadraResponse> agendarQuadra(AgendarQuadraRequest request, Authentication auth) {
        final var principal = (User) auth.getPrincipal();
        if (!principal.id().equals(request.usuarioId()) && !principal.authorities().contains("ROLE_ADMIN")) {
            throw new ForbiddenException("Usuário não autorizado");
        }

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
    public ResponseEntity<?> cancelarAgendamento(String id, Authentication auth) {
        final var agendamento = agendamentoGateway.getAgendamentoById(AgendamentoID.from(id));

        final var principal = (User) auth.getPrincipal();
        if (!principal.id().equals(agendamento.getUsuario().id()) && !principal.authorities().contains("ROLE_ADMIN")) {
            throw new ForbiddenException("Usuário não autorizado");
        }

        cancelarAgendamentoUseCase.execute(new CancelarAgendamentoUsecaseInput(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> listarAgendamentosPorQuadra(String id) {
        return ResponseEntity
                .ok(this.listarAgendamentosPorQuadraUsecase
                        .execute(new ListarAgendamentosPorQuadraUsecaseInput(id)
                        )
                );
    }

    @Override
    public ResponseEntity<?> listarAgendamentosPorUsuario(String id, Authentication authentication) {
        return ResponseEntity
                .ok(this.listarAgendamentosPorUsuarioUsecase
                        .execute(new ListarAgendamentosPorUsuarioUsecaseInput(id)
                        )
                );
    }

    @Override
    public ResponseEntity<?> reagendarAgendamento(String id, ReagendarQuadraRequest request, Authentication auth) {
        this.reagendarAgendamentoUsecase
                .execute(
                        new ReagendarAgendamentoUsecaseInput(id, request.inicioAgendamento(), request.fimAgendamento())
                );
        return ResponseEntity.noContent().build();
    }
}
