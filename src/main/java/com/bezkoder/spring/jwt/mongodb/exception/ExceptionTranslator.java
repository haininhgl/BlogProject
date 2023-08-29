package com.bezkoder.spring.jwt.mongodb.exception;

import com.bezkoder.spring.jwt.mongodb.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator {
    @ExceptionHandler(value = { MethodArgumentNotValidException.class})
    ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(APIResponse.newFailureResponse("Tham số không hợp lệ"), HttpStatus.OK);
    }

    @ExceptionHandler(value = { BadRequestException.class})
    ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(APIResponse.newFailureResponse(e.getMessage()), HttpStatus.OK);
    }
}
