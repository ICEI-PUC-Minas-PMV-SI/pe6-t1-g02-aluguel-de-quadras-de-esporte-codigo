package com.pucminas.gestaoquadras.usuarios.auth;

import org.springframework.security.core.GrantedAuthority;

public class AdminGrant implements GrantedAuthority {
    private static final AdminGrant INSTANCE = new AdminGrant();

    public static AdminGrant getInstance() {
        return INSTANCE;
    }

    @Override
    public String getAuthority() {
        return "ROLE_ADMIN";
    }
}
