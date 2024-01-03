package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.dto.JwtDto;
import com.bestoctopus.dearme.dto.UserDto;
import com.bestoctopus.dearme.dto.UserLogInRequestDto;
import io.jsonwebtoken.Claims;

public interface LogInService {
    UserDto logIn(UserLogInRequestDto userDto);

    void join(UserDto userDto);

    void isIdDuplicate(String email);

    void isNicknameDuplicate(String email);

    JwtDto generateToken(String userId);

    void logOut(String userId, String accessToken);
}
