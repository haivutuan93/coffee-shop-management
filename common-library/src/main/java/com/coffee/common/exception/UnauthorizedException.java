package com.coffee.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UnauthorizedException extends RuntimeException {
    private int errorCode;
    private String errorMessage;

    public UnauthorizedException(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = HttpStatus.UNAUTHORIZED.value();
    }

    public UnauthorizedException(int errorCode) {
        this.errorCode = errorCode;
    }
}