package com.auth.jwt.service.impl;

import com.auth.jwt.exception.InvalidTokenException;
import com.auth.jwt.model.User;
import com.auth.jwt.secrets.SecretUsages;
import com.auth.jwt.service.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Component
public record TokenServiceImpl(SecretUsages secret) implements TokenService {

    @Override
    public String generateToken(User user) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodeId = encoder.encode(user.getIdUser().toString());

        return JWT.create()
                .withIssuer(secret.issuerName)
                .withSubject(user.getUsername())
                .withClaim("id", encodeId)
                .withExpiresAt(LocalDateTime.now()
                        .plusSeconds(30)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(secret.apiSecret));
    }

    public String getUserByToken(String token)
    {
        try
        {
        return JWT.require(Algorithm.HMAC256(secret.apiSecret))
                .withIssuer(secret.issuerName)
                .build().verify(token).getSubject();
        }
        catch (Exception e)
        {
            throw new InvalidTokenException("This token is invalid!");
        }
    }

}
