package com.khanchych.tutor.components;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelpComponentLevelOne {
    private final HelpComponentLevelTwo helpComponentLevelTwo;

    public void test(String value) {
        helpComponentLevelTwo.test(value);
    }
}
