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
    private String token;

    public static UserLogInResponseDto fromEntity(UserDto userDto, String token) {
        return UserLogInResponseDto.builder()
                .user(userDto)
                .token(token)
                .build();
    }
}
