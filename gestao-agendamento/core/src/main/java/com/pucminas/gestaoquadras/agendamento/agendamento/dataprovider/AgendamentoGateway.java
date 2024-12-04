package com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider;

import com.pucminas.gestaoquadras.agendamento.agendamento.Agendamento;
import com.pucminas.gestaoquadras.agendamento.agendamento.valueobjects.AgendamentoID;
import com.pucminas.gestaoquadras.agendamento.quadra.Quadra;
import com.pucminas.gestaoquadras.agendamento.usuario.Usuario;

import java.time.Instant;
import java.util.Set;

public interface AgendamentoGateway {
    Agendamento save(Agendamento agendamento);
    Set<Agendamento> getAgendamentos();
    Set<Agendamento> getAgendamentosByUsuario(Usuario usuario);
    Set<Agendamento> getAgendamentosByQuadra(String quadraId);
    Set<String> getCountAgendamentosByQuadraEHorario(String quadraId, Instant inicioAgendamento, Instant fimAgendamento);

    Agendamento getAgendamentoById(AgendamentoID agendamentoID);

}
