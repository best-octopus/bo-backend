package com.bestoctopus.dearme.dto;

import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.exception.NotValidateException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserRequestDto {
    private String id;
    private String password;
    private String nickname;
    private String name;

    public User toEntity() {
        if (this.id == null || this.password == null || this.nickname == null || this.name == null) {
            throw new NotValidateException("유효하지 않은 입력입니다.");
        }

        return User.builder()
                .id(this.id)
                .password(this.password)
                .nickname(this.nickname)
                .name(this.name)
                .build();

    }
}
