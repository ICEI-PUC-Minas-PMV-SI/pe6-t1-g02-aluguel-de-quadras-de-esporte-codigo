package com.pucminas.gestaoquadras.agendamento.configuration.exception;

import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.AgendamentoException;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.ForbiddenException;
import com.pucminas.gestaoquadras.agendamento.agendamento.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(AgendamentoException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(AgendamentoException agendamentoException) {
        return ResponseEntity.badRequest().body(new ErrorResponse(agendamentoException.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(Exception e) {
        return ResponseEntity.status(403).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler({NotFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception notFoundException) {
        return ResponseEntity.status(404).body(new ErrorResponse(notFoundException.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUncaughtException(Exception notFoundException) {
        return ResponseEntity.status(500).body(new ErrorResponse(notFoundException.getMessage()));
    }
}
