package com.kkopaysec.assignment.banking.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final ExceptionResponse body;

    public NotFoundException(ErrorType errorType) {
        this.body = new ExceptionResponse(errorType.getErrorCode(), errorType.getMessage());
    }

}
