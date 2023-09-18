package com.coffee.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NotFoundException extends RuntimeException {
    private int errorCode;
    private String errorMessage;

    public NotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = HttpStatus.NOT_FOUND.value();
    }

    public NotFoundException(int errorCode) {
        this.errorCode = errorCode;
    }
}