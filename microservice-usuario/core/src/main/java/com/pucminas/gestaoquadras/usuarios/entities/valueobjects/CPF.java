package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;


import java.util.Objects;

public class CPF {
    private String value;

    private CPF(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void validate() {
        if (Objects.isNull(getValue()) || getValue().isEmpty()) {
            throw new IllegalArgumentException("CPF deve ser informado");
        }

        if (getValue().length() != 11) {
           throw new IllegalArgumentException("CPF deve conter 11 caracteres");
        }
    }

    public static CPF of(final String value) {
        final var cpf = new CPF(value);
        cpf.validate();
        return cpf;
    }
}
