package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.UserDto;
import com.bestoctopus.dearme.exception.NotFoundUserException;
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
    public User join(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userDto.toEntity());
    }

    @Override
    public void isIdDuplicate(String id) {
        userRepository.findById(id)
                .ifPresent(m -> {
                    throw new NotFoundUserException();
                });
    }

    @Override
    public User authenticate(UserDto userdto) {
        User user = userRepository.findById(userdto.getId())
                .orElseThrow(NotFoundUserException::new);

        if (!passwordEncoder.matches(userdto.getPassword(), user.getPassword())) {
            throw new NotFoundUserException();
        }

        return user;
    }

    @Override
    public String generateJwt(String id) {
        return jwtTokenUtil.generateToken(id);
    }
}
