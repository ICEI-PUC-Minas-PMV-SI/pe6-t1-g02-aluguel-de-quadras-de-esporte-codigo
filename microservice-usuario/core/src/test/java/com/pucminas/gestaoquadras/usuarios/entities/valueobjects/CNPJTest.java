package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CNPJTest {
    @Test
    void givenACNPJLessThan14characters_whenCreatingAnUser_thenThrowException() {
        final var aValue = "0123456789101";
        final var expectedMessage = "CNPJ deve conter 14 caracteres";

        final var exception = assertThrows(IllegalArgumentException.class, () -> CNPJ.of(aValue));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenACNPJNull_whenCreatingAnUser_thenThrowException() {
        String aValue = null;
        final var expectedMessage = "CNPJ deve ser informado";

        final var exception = assertThrows(IllegalArgumentException.class, () -> CNPJ.of(aValue));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenACNPJEmpty_whenCreatingAnUser_thenThrowException() {
        final var aValue = "";
        final var expectedMessage = "CNPJ deve ser informado";

        final var exception = assertThrows(IllegalArgumentException.class, () -> CNPJ.of(aValue));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenAnValidCNPJ_whenCreatingAnUser_thenThrowException() {
        final var aValue = "01234567891011";

        final var cnpj = CNPJ.of(aValue);

        assertEquals(aValue, cnpj.getValue());
    }
}
