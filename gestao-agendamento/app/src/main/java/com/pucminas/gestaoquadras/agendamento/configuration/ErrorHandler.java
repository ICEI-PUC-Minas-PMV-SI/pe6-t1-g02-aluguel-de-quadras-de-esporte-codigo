package com.pucminas.gestaoquadras.agendamento.configuration;

import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.AgendamentoException;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(AgendamentoException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(AgendamentoException agendamentoException) {
        return ResponseEntity.badRequest().body(new ErrorResponse(agendamentoException.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(404).body(new ErrorResponse(notFoundException.getMessage()));
    }
}
