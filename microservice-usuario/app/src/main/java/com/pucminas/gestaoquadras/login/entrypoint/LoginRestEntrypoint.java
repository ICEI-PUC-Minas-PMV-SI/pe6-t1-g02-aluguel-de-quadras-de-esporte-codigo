package com.pucminas.gestaoquadras.login.entrypoint;

import com.pucminas.gestaoquadras.login.entrypoint.request.LoginRequest;
import com.pucminas.gestaoquadras.login.entrypoint.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/login")
public interface LoginRestEntrypoint {

    @PostMapping
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request);
}
