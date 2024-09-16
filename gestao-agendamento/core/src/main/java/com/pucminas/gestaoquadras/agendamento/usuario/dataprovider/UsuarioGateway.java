package com.pucminas.gestaoquadras.agendamento.usuario.dataprovider;

import com.pucminas.gestaoquadras.agendamento.usuario.Usuario;

public interface UsuarioGateway {

    Usuario getUsuarioById(String id);
}
