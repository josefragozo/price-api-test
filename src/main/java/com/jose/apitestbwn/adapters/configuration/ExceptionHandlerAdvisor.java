package com.jose.apitestbwn.adapters.configuration;

import com.jose.apitestbwn.application.exceptions.PriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionHandlerAdvisor {

    @ExceptionHandler({PriceNotFoundException.class})
    public ResponseEntity<String> handleBadRequestException(Exception ex, WebRequest request) {

        return new ResponseEntity<>("Price Not Found", HttpStatus.BAD_REQUEST);
    }
}