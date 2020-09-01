package com.khanchych.tutor.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ServiceResponseExceptionHandler {
    private int counter;
    private boolean isStackTraceEmpty;
    private long allTime;

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Void> handleException(Exception exception) {
        counter++;
        log.error("Error during REST call - ", exception);

//        if (exception.getStackTrace().length == 0 && !isStackTraceEmpty) {
//            System.out.println("Stack trace is empty after " + counter);
//            isStackTraceEmpty = true;
//        }
//        long end = System.nanoTime();
//        long start = Long.parseLong(MDC.get("start"));
//        long diff = end - start;
//        allTime += diff;
//        MDC.clear();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
