package com.bestoctopus.dearme.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
@NoArgsConstructor
@PropertySource("classpath:application-secret.properties")
public class JwtTokenUtil {

    @Value("${secretKey}")
    private String secretKeyPlain;

    private SecretKey secretKey;

    @PostConstruct
    protected void init() {
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
        this.secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
    }

    public String generateToken(String userId){
        final long tokenValidTime = 30 * 60 * 1000L; //30분

        Claims claims = Jwts.claims().build();
        claims.put("userId",userId);

        return Jwts.builder()
                .claims(claims)
                .signWith(secretKey)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .compact();

    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmailFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("id", String.class);
    }
}
