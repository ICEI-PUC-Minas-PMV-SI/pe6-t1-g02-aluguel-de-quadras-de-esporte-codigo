package com.pucminas.gestaoquadras.agendamento.usuario.dataprovider;

import com.pucminas.gestaoquadras.agendamento.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioGatewayImpl implements UsuarioGateway {

    public UsuarioGatewayImpl() {

    }

    @Override
    public Usuario getUsuarioById(String id) {
        return new Usuario(id, id);
    }
}
