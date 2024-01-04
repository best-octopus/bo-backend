package com.bestoctopus.dearme.controller;

import com.bestoctopus.dearme.service.LogInService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/logout")
public class LogOutController {

    private final LogInService logInService;

    @Autowired
    public LogOutController(LogInService logInService) {
        this.logInService = logInService;
    }

    @PostMapping
    public ResponseEntity<?> logOut(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = (String) authentication.getPrincipal();
        String accessToken = (String) authentication.getCredentials();
        logInService.logOut(userId, accessToken);
        return ResponseEntity.ok().body("로그아웃 완료");
    }
}
