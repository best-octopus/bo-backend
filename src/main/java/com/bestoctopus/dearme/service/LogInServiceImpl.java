package com.bestoctopus.dearme.service;

import com.bestoctopus.dearme.domain.User;
import com.bestoctopus.dearme.dto.JwtDto;
import com.bestoctopus.dearme.dto.UserDto;
import com.bestoctopus.dearme.dto.UserLogInRequestDto;
import com.bestoctopus.dearme.exception.NotFoundUserException;
import com.bestoctopus.dearme.exception.NotValidateException;
import com.bestoctopus.dearme.repository.UserRepository;
import com.bestoctopus.dearme.token.JwtType;
import com.bestoctopus.dearme.token.Role;
import com.bestoctopus.dearme.util.JwtIssuer;
import com.bestoctopus.dearme.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
public class LogInServiceImpl implements LogInService {

    private final String GRANT_TYPE_BEARER = "Bearer";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtIssuer jwtIssuer;
    private final RedisUtil redisUtil;

    @Autowired
    public LogInServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtIssuer jwtIssuer, RedisUtil redisUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtIssuer = jwtIssuer;
        this.redisUtil = redisUtil;
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
    public JwtDto generateToken(String userId) {
        String accessToken = jwtIssuer.createAccessToken(userId, "ROLE_" + Role.NORMAL.getRole());
        String refreshToken = jwtIssuer.createRefreshToken(userId, "ROLE_" + Role.ADMIN.getRole());

        redisUtil.save(userId, refreshToken, Duration.ofMillis(jwtIssuer.REFRESH_DURATION));

        return JwtDto.builder()
                .grantType(GRANT_TYPE_BEARER)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void logOut(String userId, String accessToken) {
        String refreshToken = redisUtil.getValues(userId);

        Date accessTokenExpiration = jwtIssuer.parseClaimsFromToken(JwtType.ACCESS, accessToken).getExpiration();
        Date refreshTokenExpiration = jwtIssuer.parseClaimsFromToken(JwtType.REFRESH, refreshToken).getExpiration();

        long accessTokenMillis = getDurationMillis(accessTokenExpiration.getTime());
        long refreshTokenMillis = getDurationMillis(refreshTokenExpiration.getTime());

        redisUtil.save(accessToken, "logout", Duration.ofMillis(accessTokenMillis));
        redisUtil.save(refreshToken, "logout", Duration.ofMillis(refreshTokenMillis));
    }

    @Override
    public long getDurationMillis(long expiration) {
        long currentTimeMillis = System.currentTimeMillis();
        return expiration - currentTimeMillis;
    }
}


//    private String extractTokenFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
