package com.pucminas.gestaoquadras.login.entrypoint;

import com.pucminas.gestaoquadras.configuration.auth.JwtService;
import com.pucminas.gestaoquadras.login.entrypoint.request.LoginRequest;
import com.pucminas.gestaoquadras.login.entrypoint.response.LoginResponse;
import com.pucminas.gestaoquadras.login.entrypoint.response.UserDto;
import com.pucminas.gestaoquadras.usuarios.dataprovider.UsuarioJpaEntity;
import com.pucminas.gestaoquadras.usuarios.dataprovider.UsuarioMySQLRepository;
import jakarta.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController implements LoginRestEntrypoint {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioMySQLRepository userRepository;

    @Inject
    public LoginRestController(JwtService jwtService,
                               AuthenticationManager authenticationManager,
                               UsuarioMySQLRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<LoginResponse> authenticate(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.senha()));
        UsuarioJpaEntity authenticatedUser = userRepository.getUserByEmail(request.email()).orElseThrow();
        String jwtToken = jwtService.generateToken(authenticatedUser);
        final var response = new LoginResponse(jwtToken, jwtService.getExpirationTime(), UserDto.from(authenticatedUser));
        return ResponseEntity.ok(response);
    }
}

