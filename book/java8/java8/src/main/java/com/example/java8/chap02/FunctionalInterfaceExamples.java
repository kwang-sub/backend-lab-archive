package com.example.java8.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExamples {

    public static void main(String[] args) {
        Function<String, Integer> toInt = s -> Integer.parseInt(s);
        Integer apply = toInt.apply("100");
        System.out.println(apply);
        final Function<Integer, Integer> identity = Function.identity();
        Integer apply1 = identity.apply(999);
        System.out.println(apply1);

        final Consumer<String> print = s -> System.out.println(s);
        final Consumer<String> greetings = s -> System.out.println("Hello " + s);
        print.accept("kwang");
        greetings.accept("World");

        Predicate<Integer> isPositive = num -> num > 0;
        System.out.println(isPositive.test(10));
        System.out.println(isPositive.test(0));

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
        List<Integer> positiveNumbers = new ArrayList<>();
        for (Integer num : numbers) {
            if (isPositive.test(num)) {
                positiveNumbers.add(num);
            }
        }
        System.out.println("positive integers: " + positiveNumbers);
        System.out.println(filter(numbers, i -> i < 1));

        final Supplier<String> helloSupplier = () -> "hello";

        System.out.println(helloSupplier.get() + "world");

        long start = System.currentTimeMillis();
        print(0, () -> getVeryExpensiveValue());
        print(-1, () -> getVeryExpensiveValue());
        print(-2, () -> getVeryExpensiveValue());
        System.out.println("It took " + (System.currentTimeMillis() - start) / 1000 + "seconds");
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "kwang";
    }

//    private static void print(int number, String value) {
    private static void print(int number, Supplier<String> value) {
        if (number >= 0) {
            System.out.println("the value is " + value.get());
        } else {
            System.out.println("invalid");
        }
    }
    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T input : list) {
            if (filter.test(input)) {
                result.add(input);
            }
        }
        return result;
    }
}
