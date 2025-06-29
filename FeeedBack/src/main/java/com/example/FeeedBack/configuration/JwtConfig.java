package com.example.FeeedBack.configuration;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.security.SecureRandom;

@Configuration
@Getter
public class JwtConfig {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;

    @Bean
    public Key key() {
        byte[] keyBytes = new byte[32];
        new SecureRandom().nextBytes(keyBytes);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
