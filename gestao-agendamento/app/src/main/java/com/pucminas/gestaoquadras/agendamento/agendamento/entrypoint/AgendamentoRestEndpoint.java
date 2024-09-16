package com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint;

import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.requests.AgendarQuadraRequest;
import com.pucminas.gestaoquadras.agendamento.agendamento.entrypoint.responses.AgendarQuadraResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
