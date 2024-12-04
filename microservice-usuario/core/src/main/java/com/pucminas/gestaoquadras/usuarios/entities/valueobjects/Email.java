package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;

import com.pucminas.gestaoquadras.usuarios.exceptions.EmailValidationException;

import java.util.regex.Pattern;

public class Email {
    private String value;

    private Email(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void validate() {
        final var regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";
        if (!Pattern.compile(regex)
                .matcher(this.value)
                .matches()) {
            throw new EmailValidationException("O e-mail deve estar no formato adequado.");
        }
    }

    public static Email of(final String value) {
        final var email = new Email(value);
        email.validate();
       return email;
    }
}
