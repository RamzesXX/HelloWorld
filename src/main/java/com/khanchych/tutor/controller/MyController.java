package com.khanchych.tutor.controller;

import com.khanchych.tutor.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class MyController implements RestApplicationApi {
    private final PerformanceService performanceService;

    @Override
    @GetMapping("{value}")
    public void test(@PathVariable String value) {
//        MDC.put("start", String.valueOf(System.nanoTime()));
        performanceService.test(value);
    }
 
}
