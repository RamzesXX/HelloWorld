package com.khanchych.tutor;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}

//        for (int i = 0; i < 100_000; i++) {
//        try {
//        args[0].toString();
//        } catch (Exception e) {
//        if (e.getStackTrace().length == 0) {
//        System.out.format("Java ate my stacktrace after iteration #%d %n", i);
//        break;
//        }
//        }
//        }