package com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects;

import com.pucminas.gestaoquadras.agendamento.Identifier;

import java.util.Objects;
import java.util.UUID;

public class AgendamentoID extends Identifier {
    private final String value;

    private AgendamentoID(String value) {
        this.value = value;
    }

    public static AgendamentoID from(final String anId) {
        return new AgendamentoID(anId);
    }

    public static AgendamentoID unique() {
        return AgendamentoID.from(UUID.randomUUID().toString());
    }

    @Override
    public String getValue() {
        return this.value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgendamentoID that = (AgendamentoID) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
