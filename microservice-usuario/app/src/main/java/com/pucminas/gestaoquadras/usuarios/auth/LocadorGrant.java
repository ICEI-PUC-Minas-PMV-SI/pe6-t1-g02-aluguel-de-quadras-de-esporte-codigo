package com.pucminas.gestaoquadras.usuarios.auth;

import org.springframework.security.core.GrantedAuthority;

public class LocadorGrant implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ROLE_LOCADOR";
    }
}
