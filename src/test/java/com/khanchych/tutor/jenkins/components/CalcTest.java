package com.khanchych.tutor.jenkins.components;

import static org.junit.Assert.*;

public class CalcTest {
    private Calc calc;

    @org.junit.Before
    public void setUp() {
        calc = new Calc();
    }

    @org.junit.Test
    public void add() {
        int a = 1;
        int b = 5;
        int result = calc.add(a, b);
        assertEquals(a + b, result);
    }

    @org.junit.Test
    public void sub() {
        int a = 1;
        int b = 5;
        int result = calc.sub(a, b);
        assertEquals(a - b, result);
    }
}