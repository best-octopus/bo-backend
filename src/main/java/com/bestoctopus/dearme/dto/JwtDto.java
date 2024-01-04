package com.bestoctopus.dearme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class JwtDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
