package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.AgendarQuadraRequest;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.ReagendarQuadraRequest;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses.AgendarQuadraResponse;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorQuadraUsecaseOutput;
import com.pucminas.gestaoquadras.agendamento.agendamento.usecases.dto.ListarAgendamentosPorUsuarioUsecaseOutput;
import com.pucminas.gestaoquadras.agendamento.configuration.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("api/v1/agendamentos")
@Tag(name = "Agendamento")
public interface AgendamentoRestEndpoint {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Agendar quadra",
            description = "Agenda uma quadra para um horário específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento realizado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AgendarQuadraResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<?> agendarQuadra(
            @RequestBody AgendarQuadraRequest request,
            Authentication auth
    );

    @DeleteMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Cancelar agendamento",
            description = "Cancela um agendamento específico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento cancelado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<?> cancelarAgendamento(
            @PathVariable("id") String id,
            Authentication auth
    );

    @GetMapping(
            path = "quadra/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Lista agendamentos por quadra",
            description = "Lista os usuários que já agendaram a quadra"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamentos listados com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ListarAgendamentosPorQuadraUsecaseOutput.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<?> listarAgendamentosPorQuadra(@PathVariable("id") String id);


    @GetMapping(
            path = "usuario/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Lista agendamentos por usuário",
            description = "Lista as quadras já agendadas pelo usuário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamentos listados com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ListarAgendamentosPorUsuarioUsecaseOutput.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<?> listarAgendamentosPorUsuario(@PathVariable("id") String id, Authentication auth);

    @PutMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Reagenda uma quadra",
            description = "Reagenda uma quadra já agendada pelo usuário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento reagendado com sucesso",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500", description = "Erro interno",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<?> reagendarAgendamento(@PathVariable("id") String id, @RequestBody @Valid ReagendarQuadraRequest request, Authentication auth);
}
