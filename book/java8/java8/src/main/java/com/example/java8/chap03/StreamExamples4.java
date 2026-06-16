package com.example.java8.chap03;

import com.example.java8.chap03.entity.Order;
import com.example.java8.chap03.entity.OrderedItem;
import com.example.java8.chap03.entity.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

public class StreamExamples4 {
    public static void main(String[] args) {
        final List<Product> products = Arrays.asList(
                new Product(1L, "A", new BigDecimal("100.50")),
                new Product(2L, "B", new BigDecimal("23.50")),
                new Product(3L, "C", new BigDecimal("31.45")),
                new Product(4L, "D", new BigDecimal("80.20")),
                new Product(5L, "E", new BigDecimal("7.50"))
        );

        List<Product> price30 = products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .collect(toList());
        System.out.println("price30 : " + price30);

        String joining = products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .map(product -> product.toString())
                .collect(joining("\n"));
        System.out.println("\n==joining==\n" + joining);

        BigDecimal reduce = products.stream()
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, ( price1, price2) -> price1.add(price2));
        System.out.println("\nreduce : " + reduce);

        BigDecimal reduce1 = products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2));
        System.out.println("\nreduceWithFilter : " + reduce1);

        long count = products.stream()
                .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                .count();
        System.out.println("\ncount : " + count);

        OrderedItem item1 = new OrderedItem(1L, products.get(0), 1);
        OrderedItem item2 = new OrderedItem(2L, products.get(2), 3);
        OrderedItem item3 = new OrderedItem(3L, products.get(4), 10);

        Order order = new Order(1L, Arrays.asList(item1, item2, item3));
        System.out.println("\ntotalPrice : " + order.totalPrice());


    }
}

