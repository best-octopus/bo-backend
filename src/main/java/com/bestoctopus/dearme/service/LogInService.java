package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.UserDto;

public interface LogInService {
    User authenticate(UserDto userdto);
    User join(UserDto userDto);
    void isIdDuplicate(String email);
    String generateJwt(User user);
}
