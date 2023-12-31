package com.bestoctopus.dearme.util;

import com.bestoctopus.dearme.exception.JwtInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
@PropertySource("classpath:application-secret.properties")
public class JwtIssuer {
    private final int ONE_SECONDS = 1000;
    private final int ONE_MINUTE = 60 * ONE_SECONDS;
    private final String KEY_ROLES = "roles";

    private final SecretKey secretKey;
    private final SecretKey refreshSecretKey;

    public JwtIssuer(@Value("${jwt.secret}") String secretKeyPlain, @Value("${jwt.refresh-secret}") String refreshSecretKeyPlain) {
        String secretKeyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
        String refreshKeyBase64Encoded = Base64.getEncoder().encodeToString(refreshSecretKeyPlain.getBytes());
        this.secretKey = Keys.hmacShaKeyFor(secretKeyBase64Encoded.getBytes());
        this.refreshSecretKey = Keys.hmacShaKeyFor(refreshKeyBase64Encoded.getBytes());
    }

    public String createToken(String userId, String authority, int expireMin) {
        Set<String> authoritySet = new HashSet<>();
        authoritySet.add(authority);

        Claims claims = Jwts.claims().add("sub", userId).add(KEY_ROLES,authoritySet).build();

        return Jwts.builder()
                .claims(claims)
                .signWith(secretKey)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireMin))
                .compact();
    }

    public String createAccessToken(String userId, String authority) {
        return createToken(userId, authority, ONE_MINUTE * 30);
    }

    public String createRefreshToken(String userId, String authority) {
        return createToken(userId, authority, ONE_MINUTE * 120);
    }

    public Claims parseClaimsFromRefreshToken(String jwt) {
        Claims claims;
        try {
            claims = Jwts.parser().verifyWith(refreshSecretKey).build().parseSignedClaims(jwt).getPayload();
        } catch (SignatureException signatureException) {
            throw new JwtInvalidException("signature key is different");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new JwtInvalidException("expired token");
        } catch (MalformedJwtException malformedJwtException) {
            throw new JwtInvalidException("malformed token");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new JwtInvalidException("using illegal argument like null");
        }
        return claims;
    }
}
