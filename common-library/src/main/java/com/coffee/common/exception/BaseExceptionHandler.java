package com.coffee.common.exception;

import com.coffee.common.model.ErrorModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@Slf4j
@Component
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseExceptionHandler {
    @Value("${microservice.error.prefix:}")
    String prefix;

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException e, Locale locale) {
        return new ResponseEntity<>(buildErrorResponse(e.getErrorCode(), locale), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e, Locale locale) {
        return new ResponseEntity<>(buildErrorResponse(e.getErrorCode(), locale), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(ForbiddenException e, Locale locale) {
        return new ResponseEntity<>(buildErrorResponse(e.getErrorCode(), locale), HttpStatus.FORBIDDEN);
    }

    private ErrorModel buildErrorResponse(int errorCode, Locale locale){
        return ErrorModel.builder()
                .errorCode(errorCode)
                .errorMessage(messageSource.getMessage(String.valueOf(errorCode), null, locale))
                .build();
    }
}


