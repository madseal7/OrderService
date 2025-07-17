package com.example.OrderService.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;

@Service
public class JWTService {

    @Value("${secret.key}")
    private String secretKey;

    private Algorithm algorithm;

    @PostConstruct
    private void initAlgorithm() {
        this.algorithm = Algorithm.HMAC256(secretKey);
    }

    public String generateAccessToken(Long userId) {
        Instant expiryAccessToken = Instant.now().plusSeconds(60 * 60 * 24 * 7);
        return JWT.create()
                .withSubject(userId.toString())
                .withExpiresAt(Date.from(expiryAccessToken))
                .sign(algorithm);
    }

    public String generateRefreshToken(Long userId) {
        Instant expiryRefreshToken = Instant.now().plusSeconds(60 * 60 * 24);
        return JWT.create()
                .withSubject(userId.toString())
                .withExpiresAt(expiryRefreshToken)
                .sign(algorithm);
    }

    public DecodedJWT validateToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

}

