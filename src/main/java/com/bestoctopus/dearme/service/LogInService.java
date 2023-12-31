package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.dto.UserDto;
import com.bestoctopus.dearme.dto.UserLogInRequestDto;

public interface LogInService {
    UserDto logIn(UserLogInRequestDto userDto);

    void join(UserDto userDto);

    void isIdDuplicate(String email);

    void isNicknameDuplicate(String email);

    String generateToken(String userId);
}
