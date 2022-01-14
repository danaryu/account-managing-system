package com.kkopaysec.assignment.banking.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    // TODO Exception 상세처리 필요

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> NotFoundException(NotFoundException e) {
        log.info(e.getMessage());
        return ResponseEntity.badRequest().body(e.getBody());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> InternalServerException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().build();
    }

}
