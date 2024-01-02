package com.bestoctopus.dearme.controller;

import com.bestoctopus.dearme.dto.JwtDto;
import com.bestoctopus.dearme.dto.UserDto;
import com.bestoctopus.dearme.dto.UserLogInRequestDto;
import com.bestoctopus.dearme.dto.UserLogInResponseDto;
import com.bestoctopus.dearme.service.LogInService;
import com.bestoctopus.dearme.util.JwtIssuer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LogInController {

    private final LogInService logInService;
    private final JwtIssuer jwtIssuer;

    private final String AUTHORIZATION_HEADER = "Authorization";
    private static final String REFRESH_HEADER = "Refresh";

    @Autowired
    public LogInController(LogInService logInService, JwtIssuer jwtIssuer) {
        this.logInService = logInService;
        this.jwtIssuer = jwtIssuer;
    }

    @GetMapping("/id/exists")
    public ResponseEntity<?> validateId(@RequestParam @Valid String id) {
        logInService.isIdDuplicate(id);
        return ResponseEntity.ok().body("사용가능한 id입니다.");
    }

    @GetMapping("/nickname/exits")
    public ResponseEntity<?> validateNickname(@RequestParam @Valid String nickname) {
        logInService.isNicknameDuplicate(nickname);
        return ResponseEntity.ok().body("사용가능한 닉네임 입니다.");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> join(@RequestBody @Valid UserDto userDto) {
        logInService.join(userDto);
        return ResponseEntity.ok().body("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLogInResponseDto> logIn(@RequestBody @Valid UserLogInRequestDto userLogInDto) {
        UserDto userDto = logInService.logIn(userLogInDto);
        JwtDto token = logInService.generateToken(userDto.getId());
        UserLogInResponseDto response = UserLogInResponseDto.fromEntity(userDto, token);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logOut(HttpServletRequest request) {
        String accessToken = jwtIssuer.parseClaimsFromToken(request.getHeader(AUTHORIZATION_HEADER)).getSubject();
        String refreshToken = jwtIssuer.parseClaimsFromToken(request.getHeader(REFRESH_HEADER)).getSubject();
        logInService.logOut(accessToken, refreshToken);
        return ResponseEntity.ok().body("로그아웃 완료");
    }
}
