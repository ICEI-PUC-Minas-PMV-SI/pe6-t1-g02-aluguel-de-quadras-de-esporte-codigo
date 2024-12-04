package com.pucminas.gestaoquadras.usuarios.entities.valueobjects;

public class CNPJ {
    private String value;

    private CNPJ(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void validate() {
    }

    public static CNPJ of(final String value) {
        final var cnpj = new CNPJ(value);
        cnpj.validate();
        return cnpj;
    }
}
