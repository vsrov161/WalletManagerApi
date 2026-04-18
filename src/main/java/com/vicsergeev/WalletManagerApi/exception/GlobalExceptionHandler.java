package com.vicsergeev.WalletManagerApi.exception;

/*
 * Created by Vic
 * 17.04.2026
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleCustomNotFoundException(CustomNotFoundException e) {
        return Map.of("Error", e.getMessage());
    }

    @ExceptionHandler(CustomInsufficientFundsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleCustomInsufficientFundsException(CustomInsufficientFundsException e) {
        return Map.of("Error", e.getMessage());
    }

    @ExceptionHandler(CustomValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleCustomeValidateException(CustomValidateException e) {
        return Map.of("Error", e.getMessage());
    }
}
