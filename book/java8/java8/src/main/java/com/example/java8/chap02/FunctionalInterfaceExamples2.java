package com.example.java8.chap02;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceExamples2 {
    public static void main(String[] args) {
        Product product1 = new Product(1L, "a", new BigDecimal("10.00"));
        Product product2 = new Product(2L, "b", new BigDecimal("55.00"));
        Product product3 = new Product(3L, "c", new BigDecimal("12.00"));
        Product product4 = new Product(4L, "d", new BigDecimal("14.00"));
        Product product5 = new Product(5L, "e", new BigDecimal("13.00"));
        Product product6 = new Product(6L, "f", new BigDecimal("78.00"));
        final List<Product> products = Arrays.asList(
                product1,
                product2,
                product3,
                product4,
                product5,
                product6
        );
        BigDecimal twenty = new BigDecimal("20");
        List<Product> result = filter(products, product -> product.getPrice().compareTo(twenty) > 0);
        System.out.println(result);
        System.out.println(filter(products, product -> product.getPrice().compareTo(new BigDecimal("10")) == -1));

        List<Product> expensiveProducts = filter(products, product -> product.getPrice().compareTo(new BigDecimal("50")) == 1);

        List<Product> discountedProducts = map(expensiveProducts,
                product -> new DiscountedProduct(product.getId(), product.getName(),
                        product.getPrice().multiply(new BigDecimal("0.5"))));
        System.out.println(discountedProducts);

        Predicate<Product> lessThanOrEqualTo30 = product -> product.getPrice().compareTo(new BigDecimal("30")) == -1;

        System.out.println(filter(discountedProducts, lessThanOrEqualTo30));
        System.out.println(filter(products, lessThanOrEqualTo30));

        List<BigDecimal> prices = map(products, product -> product.getPrice());
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            total = total.add(price);
        }
        System.out.println("total : " + total);
        System.out.println("total2 : "  + total(products, product -> product.getPrice()));

        final BigDecimal discountedTotal = total(discountedProducts, discountedProduct -> discountedProduct.getPrice());
        System.out.println("disTotal : " + discountedTotal);

        Order order = new Order(1L, "on-1234", Arrays.asList(
            new OrderedItem(1L, product1, 2),
            new OrderedItem(2L, product2, 3),
            new OrderedItem(3L, product3, 10)
        ));
        System.out.println(order.totalPrice());
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    public static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal result = BigDecimal.ZERO;
        for (T t : list) {
            result = result.add(mapper.apply(t));
        }
        return result;
    }


    @AllArgsConstructor
    @Data
    static class Product {
        private Long id;
        private String name;
        private BigDecimal price;
    }

    @ToString(callSuper = true)
    static class DiscountedProduct extends Product {
        public DiscountedProduct(Long id, String name, BigDecimal price) {
            super(id, name, price);
        }
    }

    @AllArgsConstructor
    @Data
    static class OrderedItem{
        private Long id;
        private Product product;
        private int quantity;

        public BigDecimal getItemTotal() {
            return product.getPrice().multiply(new BigDecimal(quantity));
        }
    }

    @AllArgsConstructor
    @Data
    static class Order{
        private Long id;
        private String orderNumber;
        private List<OrderedItem> items;

        public BigDecimal totalPrice() {
            return total(items, item -> item.getItemTotal());
        }
    }
}

