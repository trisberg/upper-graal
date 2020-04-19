package com.example.upper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class Upper implements Function<String, String> {

    @Override
    public String apply(String in) {
        return in.toUpperCase();
    }
}
