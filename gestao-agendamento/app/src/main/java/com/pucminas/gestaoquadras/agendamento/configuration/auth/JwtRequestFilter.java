package com.pucminas.gestaoquadras.agendamento.configuration.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${security.jwt.secret-key}") // Your secret key
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("Filtering request");
        final String authorizationHeader = request.getHeader("Authorization");


        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            final var jwt = authorizationHeader.substring(7);
            final var claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
            System.out.println(claims);
            final var userId = claims.get("id", String.class);
            final var email = claims.get("sub", String.class);
            final var authorities = claims.get("authorities", List.class);

            final var usuario = new User(email, userId, authorities);

            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(new UsernamePasswordAuthenticationToken(usuario, email, null));
            }
        }

        chain.doFilter(request, response);
    }
}