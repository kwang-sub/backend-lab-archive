package com.example.java8.chap01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WhyJava8 {
    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        StringBuilder sb = new StringBuilder();

        final String result = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" : "));

        System.out.println(result);
    }
}
