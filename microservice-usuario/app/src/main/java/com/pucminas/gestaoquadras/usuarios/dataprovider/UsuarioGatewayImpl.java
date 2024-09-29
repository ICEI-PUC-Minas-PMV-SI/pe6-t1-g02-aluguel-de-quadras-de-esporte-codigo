package com.pucminas.gestaoquadras.usuarios.dataprovider;

import com.pucminas.gestaoquadras.usuarios.entities.Usuario;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.ListarUsuarioUseCaseOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class UsuarioGatewayImpl implements UsuarioGateway {
    private final UsuarioMySQLRepository usuarioMySQLRepository;

    @Autowired
    public UsuarioGatewayImpl(final UsuarioMySQLRepository usuarioMySQLRepository) {
        this.usuarioMySQLRepository = Objects.requireNonNull(usuarioMySQLRepository);
    }

    @Override
    public Usuario save(Usuario usuario) {
        final var jpaUsuario = UsuarioMapper.toJpa(usuario);
        final var savedUser = usuarioMySQLRepository.save(jpaUsuario);
        return UsuarioMapper.toDomain(savedUser);
    }

    @Override
    public Usuario getUser(String id) {
       final var user = usuarioMySQLRepository.findById(id);
       return UsuarioMapper.toDomain(user.get());
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {

        return usuarioMySQLRepository.getUserByEmail(email)
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public void deleteUser(String id) {
        usuarioMySQLRepository.deleteById(id);
    }

    @Override
    public List<Usuario> listUser() {
        final var userJpa= this.usuarioMySQLRepository.findAll();
        return StreamSupport
                .stream(userJpa.spliterator(), false)
                .map(UsuarioMapper::toDomain)
                .toList();


        }

}
