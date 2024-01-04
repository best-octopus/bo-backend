package com.bestoctopus.dearme.token;

import com.bestoctopus.dearme.exception.JwtInvalidException;
import com.bestoctopus.dearme.util.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
@PropertySource("classpath:application-secret.properties")
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final String KEY_ROLES = "roles";
    private SecretKey secretKey;

    private final RedisUtil redisUtil;

    public JwtAuthenticationProvider(@Value("${jwt.secret}") String secretKeyPlain, RedisUtil redisUtil) {
        String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
        this.secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
        this.redisUtil = redisUtil;
    }

    private Collection<? extends GrantedAuthority> createGrantedAuthorities(Claims claims) {
        List<String> roles = (List) claims.get(KEY_ROLES);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String role : roles) {
            grantedAuthorities.add(() -> role);
        }
        return grantedAuthorities;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        Claims claims;
        try {
            claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims((String) authentication.getCredentials()).getPayload();
        } catch (SignatureException signatureException) {
            throw new JwtInvalidException("signature key is different");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new JwtInvalidException("expired token");
        } catch (MalformedJwtException malformedJwtException) {
            throw new JwtInvalidException("malformed token");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new JwtInvalidException("using illegal argument like null");
        }

        if (redisUtil.getValues(token)!=null && redisUtil.getValues(token).equals("logout")) {
            throw new JwtInvalidException("로그아웃한 유저");
        }

        return new JwtAuthenticationToken(claims.getSubject(), (String) authentication.getCredentials(), createGrantedAuthorities(claims));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

//    public String generateToken(String userId) {
//        final long tokenValidTime = 30 * 60 * 1000L; //30분
//
//        Claims claims = Jwts.claims().add("userId", userId).build();
////        claims.put("userId", userId);
//
//        return Jwts.builder()
//                .claims(claims)
//                .signWith(secretKey)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + tokenValidTime))
//                .compact();
//    }
//
//    public Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .verifyWith(secretKey)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//
//    public String getIdFromToken(String token) {
//        Claims claims = extractAllClaims(token);
//        return claims.get("id", String.class);
//    }
}
