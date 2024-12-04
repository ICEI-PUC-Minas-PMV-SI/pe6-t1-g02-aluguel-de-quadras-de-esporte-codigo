package com.pucminas.gestaoquadras.usuarios.auth;

import org.springframework.security.core.GrantedAuthority;

public class LocatarioGrant implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ROLE_LOCATARIO";
    }
}
