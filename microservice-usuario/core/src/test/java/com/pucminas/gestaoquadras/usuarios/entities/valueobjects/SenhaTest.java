package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SenhaTest {
    @Test
    void givenAPasswordLessThan8characters_whenCreatingAnUser_thenThrowException() {
        final var aValue = "1234567";
        final var expectedMessage = "A senha não pode ser menor que 8 caracteres";

        final var exception = assertThrows(IllegalArgumentException.class, () -> Senha.of(aValue));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenAPasswordEmpty_whenCreatingAnUser_thenThrowException() {
        final var aValue = "";
        final var expectedMessage = "Por favor, defina uma senha";

        final var exception = assertThrows(IllegalArgumentException.class, () -> Senha.of(aValue));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenValidPassword_whenCreatingAnUser_thenReturnPassword() {
        final var aValue = "12345678";
        final var senha = Senha.of(aValue);

        assertEquals(aValue, senha.getValue());
    }
}
