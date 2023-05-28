package com.example.pagenumbersreducer.controller;

import com.example.pagenumbersreducer.exception.PageValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
    private static final String HANDLED_EXCEPTION = "Handled exception: ";

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(HANDLED_EXCEPTION, e);

        if (e instanceof PageValidationException) {
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .internalServerError()
                .body(e.getMessage());
    }
}
