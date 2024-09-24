package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.AgendarQuadraRequest;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.ListarAgendamentosPorUsuarioRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("api/v1/agendamentos")
public interface AgendamentoRestEndpoint {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> agendarQuadra(
            @RequestBody AgendarQuadraRequest request
    );

    @DeleteMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    ) ResponseEntity<?> cancelarAgendamento(
            @PathVariable("id") String id
    );

    @GetMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    ) ResponseEntity<Set<Agendamento>> listarAgendamentosPorUsuario(
            @RequestBody ListarAgendamentosPorUsuarioRequest request
    );

    @GetMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    ) ResponseEntity<Set<Agendamento>> listarAgendamentos();
}
