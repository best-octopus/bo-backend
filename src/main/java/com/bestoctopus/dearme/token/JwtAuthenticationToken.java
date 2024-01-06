package com.bestoctopus.dearme.token;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken implements Authentication {
    //    private String jwt;
    private Object principal;
    private String credentials;
    private Object details;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean authenticated;

    public JwtAuthenticationToken(String jwt) {
        this.credentials = jwt;
        setAuthenticated(false);
    }

    public JwtAuthenticationToken(Object principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.principal = principal;
        this.credentials = credentials;
        this.details = null;
        setAuthenticated(true);
    }


    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }
}
