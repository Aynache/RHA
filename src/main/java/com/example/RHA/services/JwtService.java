package com.example.RHA.services;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Component
public class JwtService {

    private SecretKey key;
    private static final Duration EXPIRY = Duration.ofDays(1);

    @PostConstruct
    void init() {
        key = Jwts.SIG.HS256.key().build();
    }

    public String generate(String username, Set<String> roles) {

        Instant now = Instant.now();

        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(EXPIRY)))
                .signWith(key)
                .compact();
    }


    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }


    public boolean isValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}