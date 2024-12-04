package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void givenAnInvalidEmail_whenCreatingAnEmail_thenThrowException() {
     final var aValue = "testegmail.com";
     final var expectedMessage = "O e-mail deve estar no formato adequado.";

     final var exception = assertThrows(RuntimeException.class, () -> Email.of(aValue));
     assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void givenAnValidEmail_whenCreatingAnEmail_thenReturnEmail() {
        final var aValue = "teste@gmail.com";

        final var email = Email.of(aValue);

        assertEquals(aValue, email.getValue());
    }
}