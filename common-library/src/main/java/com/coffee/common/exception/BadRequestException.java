package com.coffee.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BadRequestException extends RuntimeException {
    private int errorCode;
    private String errorMessage;

    public BadRequestException(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = HttpStatus.BAD_REQUEST.value();
    }

    public BadRequestException(int errorCode) {
        this.errorCode = errorCode;
    }
}