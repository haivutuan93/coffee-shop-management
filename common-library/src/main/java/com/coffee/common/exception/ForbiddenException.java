package com.coffee.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ForbiddenException extends RuntimeException {
    private int errorCode;
    private String errorMessage;

    public ForbiddenException(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = HttpStatus.FORBIDDEN.value();
    }

    public ForbiddenException(int errorCode) {
        this.errorCode = errorCode;
    }
}
