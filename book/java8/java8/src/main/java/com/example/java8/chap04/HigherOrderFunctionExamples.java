package com.example.java8.chap04;

import java.util.function.Function;

public class HigherOrderFunctionExamples {
    public static void main(String[] args) {
        final Function<Function<Integer, String>, String> f = g -> g.apply(10);
        String a = f.apply(i -> "#" + i);
        System.out.println(a);

        final Function<Integer, Function<Integer, Integer>> f2 = i -> (i2 -> i + i2);
        System.out.println(f2.apply(2).apply(19));
    }
 }
