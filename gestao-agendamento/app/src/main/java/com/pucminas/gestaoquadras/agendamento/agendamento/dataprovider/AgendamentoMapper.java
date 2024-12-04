package com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;

public class AgendamentoMapper {

    public static Agendamento toDomain(AgendamentoJpaEntity jpa) {
        return Agendamento.with(
                jpa.getId(),
                jpa.getStatus(),
                jpa.getQuadra(),
                jpa.getUsuario(),
                jpa.getInicioAgendamento(),
                jpa.getFimAgendamento()
        );
    }

    public static AgendamentoJpaEntity toJpa(Agendamento domain) {
        return new AgendamentoJpaEntity(
                domain.getId().getValue(),
                domain.getStatus().name(),
                domain.getQuadra(),
                domain.getUsuario().id(),
                domain.getInicioAgendamento(),
                domain.getFimAgendamento()
        );
    }
}
