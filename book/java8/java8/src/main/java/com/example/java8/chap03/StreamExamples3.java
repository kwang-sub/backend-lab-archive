package com.example.java8.chap03;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExamples3 {
    public static void main(String[] args) {
        List<String> collect = Stream.of(1, 2, 3, 4, 5, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(Collectors.toList());
        System.out.println("toList : " + collect);

        Set<String> collect1 = Stream.of(1, 2, 3, 4, 5, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(Collectors.toSet());
        System.out.println("toSet :" + collect1);

        String joining = Stream.of(1, 2, 3, 4, 5, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(Collectors.joining(", "));
        System.out.println("joining :" + joining);

        String joining2 = Stream.of(1, 2, 3, 4, 5, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("joining2 :" + joining2);

        List<String> collect2 = Stream.of(1, 2, 3, 4, 5, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("collect2 :" + collect2);

        Integer test = 190;
        Optional<Integer> first = Stream.of(1, 2, 3, 4, 5, 5, 5, 190)
                .filter(i -> i.equals(test))
                .findFirst();
        System.out.println("first :" + first);


        long count = Stream.of(1, 2, 3, 4, 5, 5, 5, 190)
                .filter(i -> i > 3)
                .count();
        System.out.println("count :" + count);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().forEach(i -> System.out.println(i));

    }
}
