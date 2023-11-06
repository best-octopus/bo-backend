package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private String id;
    private String password;
    private String nickname;
    private String name;

    public User toEntity(){
        return User.builder()
                .id(this.id)
                .password(this.password)
                .nickname(this.nickname)
                .name(this.name)
                .build();

    }
}
