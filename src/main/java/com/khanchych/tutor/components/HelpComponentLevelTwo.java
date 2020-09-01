package com.khanchych.tutor.components;

import org.springframework.stereotype.Component;

@Component
public class HelpComponentLevelTwo {

    public void test(String value) {
        if (value.length() % 2 == 1) {
            throw new NullPointerException();
        }
    }
}
