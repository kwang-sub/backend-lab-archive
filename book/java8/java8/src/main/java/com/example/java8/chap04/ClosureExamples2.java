package com.example.java8.chap04;

public class ClosureExamples2 {
    private int number = 999;

    public static void main(String[] args) {
        new ClosureExamples2().test();
    }

    private void test() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        };
        runnable.run();

        Runnable runnable1 = () -> System.out.println(this.number);
        runnable1.run();
    }
}
