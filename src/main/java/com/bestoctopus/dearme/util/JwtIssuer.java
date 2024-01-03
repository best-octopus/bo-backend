package com.bestoctopus.dearme.util;

import com.bestoctopus.dearme.exception.JwtInvalidException;
import com.bestoctopus.dearme.token.JwtType;
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
    public final int ACCESS_DURATION = 30 * ONE_MINUTE;
    public final int REFRESH_DURATION = 240 * ONE_MINUTE;
    private final String KEY_ROLES = "roles";

    private final SecretKey secretKey;
    private final SecretKey refreshSecretKey;


    public JwtIssuer(@Value("${jwt.secret}") String secretKeyPlain, @Value("${jwt.refresh-secret}") String refreshSecretKeyPlain) {
        String secretKeyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
        String refreshKeyBase64Encoded = Base64.getEncoder().encodeToString(refreshSecretKeyPlain.getBytes());
        this.secretKey = Keys.hmacShaKeyFor(secretKeyBase64Encoded.getBytes());
        this.refreshSecretKey = Keys.hmacShaKeyFor(refreshKeyBase64Encoded.getBytes());
    }

    public String createToken(JwtType jwtType, String userId, String authority, int expireMin) {
        SecretKey secretKeyForSign = secretKey;
        if (jwtType.equals(JwtType.REFRESH)) {
            secretKeyForSign = refreshSecretKey;
        }
        Set<String> authoritySet = new HashSet<>();
        authoritySet.add(authority);

        Claims claims = Jwts.claims().add("sub", userId).add(KEY_ROLES, authoritySet).build();

        return Jwts.builder()
                .claims(claims)
                .signWith(secretKeyForSign)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireMin))
                .compact();
    }

    public String createAccessToken(String userId, String authority) {
        return createToken(JwtType.ACCESS, userId, authority, ACCESS_DURATION);
    }

    public String createRefreshToken(String userId, String authority) {
        return createToken(JwtType.REFRESH, userId, authority, REFRESH_DURATION);
    }

    public Claims parseClaimsFromToken(JwtType jwtType, String token) {
        Claims claims = null;
        try {
            if (jwtType.equals(JwtType.ACCESS)) {
                claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
            } else if (jwtType.equals(JwtType.REFRESH)) {
                claims = Jwts.parser().verifyWith(refreshSecretKey).build().parseSignedClaims(token).getPayload();
            }
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
