package com.pucminas.gestaoquadras.usuarios.dataprovider;

import com.pucminas.gestaoquadras.usuarios.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioGateway {

    Usuario save(Usuario usuario);
    Usuario getUser(String id);
    Optional<Usuario> getUserByEmail(String email);
    void deleteUser(String id);

    List<Usuario> listUser();

}
