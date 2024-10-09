package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;

import java.util.Objects;

public class CNPJ {
    private String value;

    private CNPJ(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void validate() {
        if (Objects.isNull(getValue()) || getValue().isEmpty()) {
            throw new IllegalArgumentException("CNPJ deve ser informado");
        }

        if (getValue().length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter 14 caracteres");
        }
    }

    public static CNPJ of(final String value) {
        final var cnpj = new CNPJ(value);
        cnpj.validate();
        return cnpj;
    }
}
