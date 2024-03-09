package com.june.apiserver.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noExist(NoSuchElementException e) {
        log.error("error = {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
    }
    //  NoResourceFoundException
    //  MethodArgumentTypeMismatchException
    //  MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> no(MethodArgumentNotValidException e) {
        log.error("error = {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("message", e.getMessage()));
    }

}