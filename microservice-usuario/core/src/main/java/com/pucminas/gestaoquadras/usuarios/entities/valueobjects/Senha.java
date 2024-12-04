package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;

public class Senha {
    private String value;

    private Senha(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void validate() {
    }

    public static Senha of(final String value) {
        final var senha = new Senha(value);
        senha.validate();
        return senha;
    }
}
