package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.UserDto;
import com.bestoctopus.dearme.dto.UserLogInRequestDto;
import com.bestoctopus.dearme.exception.NotFoundUserException;
import com.bestoctopus.dearme.exception.NotValidateException;
import com.bestoctopus.dearme.repository.UserRepository;
import com.bestoctopus.dearme.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImpl implements LogInService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public LogInServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void join(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userDto.toEntity());
    }

    @Override
    public void isIdDuplicate(String id) {
        userRepository.findById(id)
                .ifPresent(m -> {
                    throw new NotValidateException("이미 존재하는 id 입니다.");
                });
    }

    @Override
    public void isNicknameDuplicate(String nickname) {
        userRepository.findByNickname(nickname)
                .ifPresent(m -> {
                    throw new NotValidateException("이미 존재하는 nickname 입니다.");
                });
    }

    @Override
    public UserDto logIn(UserLogInRequestDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(NotFoundUserException::new);

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new NotFoundUserException();
        }

        return UserDto.fromEntity(user);
    }

    @Override
    public String generateJwt(String id) {
        return jwtTokenUtil.generateToken(id);
    }
}
