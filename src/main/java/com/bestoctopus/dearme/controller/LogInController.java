package com.bestoctopus.dearme.controller;

import com.bestoctopus.dearme.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class LogInController {

    private final LogInService logInService;

    @Autowired
    public LogInController(LogInService logInService){
        this.logInService = logInService;
    }

    @GetMapping
    public ResponseEntity<?> validate(@RequestParam String id){
            logInService.validateId(id);
            return ResponseEntity.ok()
                    .build();
    }
}
