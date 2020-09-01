package com.khanchych.tutor.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ServiceResponseExceptionHandler {
    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Void> handleException(Exception exception) {
        log.error("Error during REST call - ", exception);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
