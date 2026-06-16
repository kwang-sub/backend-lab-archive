package com.example.java8.chap05;

import com.example.java8.chap03.entity.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodReferenceExamples {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5)
                .forEach(System.out::println);

        List<BigDecimal> collect = Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                .stream()
//                .sorted(BigDecimalUtil::compare)
                .sorted(BigDecimal::compareTo)
                .collect(Collectors.toList());
        System.out.println(collect);

        boolean b = Arrays.asList("a", "b", "c", "d")
                .stream()
//                .anyMatch(s -> s.equals("c"));
                .anyMatch("c"::equals);
        System.out.println(b);

        List<Product> collect1 = Arrays.asList(
                        new Product(1L, "1", new BigDecimal(1)),
                        new Product(2L, "2", new BigDecimal(2)),
                        new Product(3L, "3", new BigDecimal(3)),
                        new Product(4L, "4", new BigDecimal(4))
                ).stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .collect(Collectors.toList());
        System.out.println(collect1);

        System.out.println("========================");
        methodReference03();
        System.out.println(getDoubleThenToString().apply(3));
        System.out.println(getDoubleThenToStringMethod().apply(3));

        final List<Function<Integer, String>> fsL = Arrays.asList(
          i -> String.valueOf( i * 2),
          MethodReferenceExamples::doubleThenToString
        );
        System.out.println("List test");
        for (Function<Integer, String> f : fsL) {
            f.apply(2);
        }
    }

    private static String doubleThenToString(int n) {
        return String.valueOf(n * 2);
    }

    private static void methodReference03() {
        System.out.println(testFirstClassFunction1(3, MethodReferenceExamples::doubleThenToString));
    }
    private static String testFirstClassFunction1(int n, Function<Integer, String> f) {
        return "The result is " + f.apply(n);
    }

    private static Function<Integer, String> getDoubleThenToString() {
        return i -> String.valueOf(i * 2);
    }

    private static Function<Integer, String> getDoubleThenToStringMethod() {
        return MethodReferenceExamples::doubleThenToString;
    }
}

class BigDecimalUtil {
    public static int compare(BigDecimal db1, BigDecimal db2) {
        return db1.compareTo(db2);
    }
}
