package com.example.java8.chap04;

import lombok.ToString;

@ToString
public class ClosureExamples {
    private int number = 999;
    public static void main(String[] args) {;
        new ClosureExamples().test1();
    }
    private void test1() {
        int number = 100;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(this.toString());
            }
        };
        Runnable runnable2 = () -> System.out.println(this.number);
        testClosure("Anonymous", runnable);
        testClosure("Lambda", runnable2);
    }

    private void test2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(ClosureExamples.this.toString("test"));
            }
        };
        Runnable runnable2 = () -> System.out.println(toString("test"));
        testClosure("Anonymous", runnable);
        testClosure("Lambda", runnable2);
    }

    private void test3() {
        int number = 100;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int number = 50;
                System.out.println(number);
            }
        };

        Runnable runnable2 = () -> {
            System.out.println(number);
        };

        testClosure("Anonymous", runnable);
        testClosure("Lambda", runnable2);
    }

    public static <T> String toString(T value) {
        return "The value is " + String.valueOf(value);
    }

    public static void testClosure(String name, Runnable runnable) {
        System.out.println(name +" : ");
        runnable.run();
    }
}
