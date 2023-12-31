package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.UserDto;
import com.bestoctopus.dearme.dto.UserLogInRequestDto;
import com.bestoctopus.dearme.exception.NotFoundUserException;
import com.bestoctopus.dearme.exception.NotValidateException;
import com.bestoctopus.dearme.repository.UserRepository;
import com.bestoctopus.dearme.util.JwtIssuer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImpl implements LogInService {

    //    private final String AUTHORIZATION_HEADER = "Authorization";
    //    public final String BEARER_PREFIX = "Bearer ";
    private final String ROLE_NORMAL = "NORMAL";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtIssuer jwtIssuer;

    @Autowired
    public LogInServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtIssuer jwtIssuer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtIssuer = jwtIssuer;
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
    public String generateToken(String userId) {
        return jwtIssuer.createAccessToken(userId, "ROLE_"+ROLE_NORMAL);
    }

//    private String extractTokenFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
}
