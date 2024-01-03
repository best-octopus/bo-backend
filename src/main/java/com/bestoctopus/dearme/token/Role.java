package com.bestoctopus.dearme.token;

import lombok.Getter;

@Getter
public enum Role {
    NORMAL("NORMAL"), ADMIN("ADMIN");

    private final String role;

    Role(String role){
        this.role = role;
    }
}
