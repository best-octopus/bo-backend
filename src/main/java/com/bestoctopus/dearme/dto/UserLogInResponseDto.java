package com.bestoctopus.dearme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserLogInResponseDto {
    private UserDto user;
    private JwtDto jwt;

    public static UserLogInResponseDto fromEntity(UserDto userDto, JwtDto jwtDto) {
        return UserLogInResponseDto.builder()
                .user(userDto)
                .jwt(jwtDto)
                .build();
    }
}
