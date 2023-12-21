package com.bestoctopus.dearme.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
@NoArgsConstructor
public class JwtTokenUtil {
    private final String secretKey = "best-octopus";
    private Key key;

    @PostConstruct
    protected void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(String userId){
        final long tokenValidTime = 30 * 60 * 1000L; //30분

        Claims claims = Jwts.claims().build();
        claims.put("userId",userId);

        return Jwts.builder()
                .claims(claims)
                .signWith(key)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .compact();

    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey()
    }

    public String getEmailFromToken(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("id", String.class);
    }


}
