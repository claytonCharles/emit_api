package com.clayton.emit_api.core.data.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.clayton.emit_api.auth.domain.entities.UserEntity;

@Service
public class TokenService {

    @Value("${api.security.secret.key}")
    private String secretKey;

    /**
     * Gera um token para o usuário consegui utilizar as funcionalidades da API.
     * @param user {@link UserEntity}
     * @return {@link String}
     * @author Clayton Charles
     * @version 0.1.0
     */
    public String generateTokenJWT(UserEntity user) {
        try {
            Instant expirationDate = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String tokenJWT = JWT.create()
                                    .withIssuer("emit-api")
                                    .withSubject(user.getMail())
                                    .withExpiresAt(expirationDate)
                                    .sign(algorithm);
            return tokenJWT;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Não foi possivel criar o token de acesso devido a Erro:", exception);
        }
    }

    /**
     * Valida se o token enviado e valido para acessar as funcionalidades da API.
     * @param tokenJWT {@link String}
     * @return {@link String}
     * @author Clayton Charles
     * @version 0.1.0
     */
    public String validateTokenJWT(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String userMail = JWT.require(algorithm)
                                    .withIssuer("emit-api")
                                    .build()
                                    .verify(tokenJWT)
                                    .getSubject();
            return userMail;
        } catch (JWTVerificationException exception) {
            return "";
        }
    }
}
