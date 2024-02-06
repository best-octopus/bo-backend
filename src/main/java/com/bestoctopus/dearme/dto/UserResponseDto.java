package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserResponseDto {
    private String id;
    private String nickname;
    private String name;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .name(user.getName())
                .build();

    }
}
