package com.example.java8.chap02;

public class Test {
    public static void main(String[] args) {
        System.out.println(stringToInt("A", s -> (int)s.charAt(0)));
    }

    public static int stringToInt(String s, TestInt function) {
        return function.test(s);
    }
}

@FunctionalInterface
interface TestInt {
    Integer test(String s);
}
