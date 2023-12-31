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
        errorResponse.put("errorMessage", "유효하지 않은 정보입니다.");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NotValidateException.class)
    public ResponseEntity<Object> handlerNotValidateException(NotValidateException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", "NOT_VALIDATE");
        errorResponse.put("errorMessage", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(JwtInvalidException.class)
    public ResponseEntity<Object> handlerJwtInvalidException(JwtInvalidException ex){
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("errorCode", "INVALID_JWT");
        errorResponse.put("errorMessage", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
