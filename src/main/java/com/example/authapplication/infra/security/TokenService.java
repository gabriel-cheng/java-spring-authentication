package com.example.authapplication.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.authapplication.domain.users.Users;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Users user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            String token = JWT.create()
                .withIssuer("login_auth_api")
                .withSubject(user.getEmail())
                .withExpiresAt(this.generateExpirationDate())
                .sign(algorithm);

                return token;
        } catch(JWTCreationException jwtErrorException) {
            throw new RuntimeException("Error while authentication: " + jwtErrorException.getMessage());
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            return JWT.require(algorithm)
                .withIssuer("login_auth_api")
                .build()
                .verify(token)
                .getSubject();
        } catch(JWTVerificationException jwtVerificationException) {
            System.out.println("JWT Verification error: " + jwtVerificationException.getMessage());

            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
