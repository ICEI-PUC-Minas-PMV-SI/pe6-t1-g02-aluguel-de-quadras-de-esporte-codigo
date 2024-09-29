package com.pucminas.gestaoquadras.usuarios.dataprovider;


import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioMySQLRepository extends CrudRepository<UsuarioJpaEntity, String> {

    Optional<UsuarioJpaEntity> getUserByEmail(String email);
}
