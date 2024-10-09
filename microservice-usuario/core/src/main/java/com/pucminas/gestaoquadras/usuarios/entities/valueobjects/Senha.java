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

        if (getValue().isEmpty()) {
            throw new IllegalArgumentException("Por favor, defina uma senha");
        }

        if (getValue().length() < 8) {
            throw new IllegalArgumentException("A senha não pode ser menor que 8 caracteres");
        }
    }

    public static Senha of(final String value) {
        final var senha = new Senha(value);
        senha.validate();
        return senha;
    }
}
