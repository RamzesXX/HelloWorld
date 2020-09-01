package com.khanchych.tutor.service;

import com.khanchych.tutor.components.HelpComponentLevelOne;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {
    private final HelpComponentLevelOne helpComponentLevelOne;

    @Override
    public void test(String value) {
        helpComponentLevelOne.test(value);
    }
}
