package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;


public class CPF {
    private String value;

    private CPF(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void validate() {
    }

    public static CPF of(final String value) {
        final var cpf = new CPF(value);
        cpf.validate();
        return cpf;
    }
}
