package com.bestoctopus.dearme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<Object> handlerFileUploadException(NotFoundUserException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", "NOT_FOUND_USER");
        errorResponse.put("errorMessage", "해당하는 유저를 찾지 못했습니다.");
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
