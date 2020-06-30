package com.khanchych.tutor.jenkins;

import com.khanchych.tutor.jenkins.components.Calc;
import org.modelmapper.ModelMapper;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        HashMap<String,String> configurationMap = new HashMap<String, String>();
        configurationMap.put("someStringValue", "string-string");
        configurationMap.put("someIntegerValue", "1254");

        Calc calc =  modelMapper.map(configurationMap, Calc.class);

        System.out.println(calc);
    }
}
