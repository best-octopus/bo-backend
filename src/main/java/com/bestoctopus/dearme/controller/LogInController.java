package com.bestoctopus.dearme.controller;

import com.bestoctopus.dearme.dto.UserDto;
import com.bestoctopus.dearme.dto.UserLogInRequestDto;
import com.bestoctopus.dearme.dto.UserLogInResponseDto;
import com.bestoctopus.dearme.service.LogInService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LogInController {

    private final LogInService logInService;

    @Autowired
    public LogInController(LogInService logInService) {
        this.logInService = logInService;
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
        String token = logInService.generateToken(userDto.getId());
        UserLogInResponseDto response = UserLogInResponseDto.fromEntity(userDto, token);
        return ResponseEntity.ok().body(response);
    }
}
