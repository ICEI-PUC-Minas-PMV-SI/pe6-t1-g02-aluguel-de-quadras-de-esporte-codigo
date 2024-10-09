package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CPFTest {

    @Test
    void givenACPFLessThan11characters_whenCreatingAnUser_thenThrowException() {
        final var aValue = "0123456789";
        final var expectedMessage = "CPF deve conter 11 caracteres";

        final var exception = assertThrows(IllegalArgumentException.class, () -> CPF.of(aValue));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenACPFNull_whenCreatingAnUser_thenThrowException() {
        String aValue = null;
        final var expectedMessage = "CPF deve ser informado";

        final var exception = assertThrows(IllegalArgumentException.class, () -> CPF.of(aValue));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenACPFEmpty_whenCreatingAnUser_thenThrowException() {
        final var aValue = "";
        final var expectedMessage = "CPF deve ser informado";

        final var exception = assertThrows(IllegalArgumentException.class, () -> CPF.of(aValue));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenAnValidCPF_whenCreatingAnUser_thenReturnCPF() {
        final var aValue = "12345678910";

        final var cpf = CPF.of(aValue);

        assertEquals(aValue, cpf.getValue());
    }
}
