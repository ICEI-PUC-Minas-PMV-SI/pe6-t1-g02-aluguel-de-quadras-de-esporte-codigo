package com.pucminas.gestaoquadras.agendamento.agendamento.dataprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AgendamentoMySQLRepository extends JpaRepository<AgendamentoJpaEntity, String> {

    @Query(value = "SELECT COUNT(*) FROM agendamentos WHERE quadra = :quadra AND inicioAgendamento > :inicio AND fimAgendamento < :fim", nativeQuery = true)
    Integer getCountAgendamentosByQuadraEHorario(@Param("quadra") String quadra, @Param("inicio") String inicio, @Param("fim") String fim);
    Set<AgendamentoJpaEntity> getAgendamentosByUsuario(String usuario);

    Set<AgendamentoJpaEntity> findAgendamentosByQuadra(String quadra);
}
