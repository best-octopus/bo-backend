package com.bestoctopus.dearme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLogInRequestDto {
    private String id;
    private String password;
}
