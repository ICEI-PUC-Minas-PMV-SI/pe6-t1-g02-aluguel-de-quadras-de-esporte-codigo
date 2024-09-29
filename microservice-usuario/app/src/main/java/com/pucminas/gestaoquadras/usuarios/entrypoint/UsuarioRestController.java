package com.pucminas.gestaoquadras.usuarios.entrypoint;

import com.pucminas.gestaoquadras.usuarios.auth.AdminGrant;
import com.pucminas.gestaoquadras.usuarios.dataprovider.UsuarioJpaEntity;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CNPJ;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.CPF;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Email;
import com.pucminas.gestaoquadras.usuarios.entities.valueobjects.Senha;
import com.pucminas.gestaoquadras.usuarios.entrypoint.requests.CreateUserRequest;
import com.pucminas.gestaoquadras.usuarios.entrypoint.requests.UpdateUserRequest;
import com.pucminas.gestaoquadras.usuarios.entrypoint.responses.CreateUserResponse;
import com.pucminas.gestaoquadras.usuarios.exceptions.ForbiddenException;
import com.pucminas.gestaoquadras.usuarios.usecases.create.*;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.CreateUsuarioUseCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.DeleteUsuarioCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.GetUsuarioUseCaseInput;
import com.pucminas.gestaoquadras.usuarios.usecases.create.dto.UpdateUsuarioCaseInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UsuarioRestController implements UsuarioRestEntrypoint {
    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final UpdateUsuarioUseCase updateUsuarioUseCase;
    private final ListarUsuarioUseCase listarUsuarioUseCase;
    private final DeleteUsuarioUseCase deleteUsuarioUseCase;
    private final GetUsuarioUseCase getUsuarioUseCase;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioRestController(final CreateUsuarioUseCase createUsuarioUseCase,
                                 final UpdateUsuarioUseCase updateUsuarioUseCase,
                                 final ListarUsuarioUseCase listarUsuarioUseCase,
                                 final GetUsuarioUseCase getUsuarioUseCase,
                                 final DeleteUsuarioUseCase deleteUsuarioUseCase,
                                 final PasswordEncoder passwordEncoder) {
        this.createUsuarioUseCase = Objects.requireNonNull(createUsuarioUseCase);
        this.updateUsuarioUseCase = Objects.requireNonNull(updateUsuarioUseCase);
        this.listarUsuarioUseCase = Objects.requireNonNull(listarUsuarioUseCase);
        this.getUsuarioUseCase = Objects.requireNonNull(getUsuarioUseCase);
        this.deleteUsuarioUseCase = Objects.requireNonNull(deleteUsuarioUseCase);
        this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
    }

    @Override
    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest body) {
        final var createUsecaseInput = new CreateUsuarioUseCaseInput(
                Senha.of(passwordEncoder.encode(body.senha())),
                Email.of(body.email()),
                body.telefone(),
                body.nome(),
                CNPJ.of(body.cnpj()),
               CPF.of(body.cpf())
        );

        final var createUsecaseOutput = this.createUsuarioUseCase.create(createUsecaseInput);

        return ResponseEntity.status(201).
                body(CreateUserResponse.from(createUsecaseOutput));
    }

    @Override
    public ResponseEntity<?> updateUser(UpdateUserRequest body, String id, Authentication auth) {
        final var principal = (UsuarioJpaEntity) auth.getPrincipal();
        if (!principal.getId().equals(id) && !principal.getAuthorities().contains(AdminGrant.getInstance())) {
            throw new ForbiddenException("Usuário não autorizado");
        }

        final var updateUsecaseInput = new UpdateUsuarioCaseInput(
                id,
                Senha.of(body.senha()),
                body.telefone(),
                body.nome()
        );

        this.updateUsuarioUseCase.update(updateUsecaseInput);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> listUser() {
        return ResponseEntity.ok(this.listarUsuarioUseCase.listar());
    }

    @Override
    public ResponseEntity<?> getUser(String id) {
        return ResponseEntity.ok(this.getUsuarioUseCase.get(new GetUsuarioUseCaseInput(id)));
    }

    @Override
    public ResponseEntity<?> deleteUser(String id, Authentication auth) {
        final var principal = (UsuarioJpaEntity) auth.getPrincipal();
        if (!principal.getId().equals(id) && !principal.getAuthorities().contains(AdminGrant.getInstance())) {
            throw new ForbiddenException("Usuário não autorizado");
        }

        this.deleteUsuarioUseCase.delete(new DeleteUsuarioCaseInput(id));
        return ResponseEntity.ok().build();
    }
}
