package com.example.java8.chap05;

import com.example.java8.chap03.entity.Product;
import com.example.java8.chap03.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.function.Function;

public class MethodReferenceExamples2Constructor {
    public static void main(String[] args) {
        final Section section1 = new Section(1);

        final Function<Integer, Section> sectionFunction = number -> new Section(number);
        final Section section2 = sectionFunction.apply(2);
        System.out.println(section1);
        System.out.println(section2);

        final Function<Integer, Section> sectionFunction1 = Section::new;
        final Section section3 = sectionFunction1.apply(3);
        System.out.println(section3);

        ProductA a = createProduct(1L, "A", new BigDecimal("123"), ProductA::new);
    }

    private static <T extends Product> T createProduct(Long id, String name, BigDecimal price, ProductCreator<T> productCreator) {
        if (id == null || id < 1L) {
            throw new IllegalArgumentException("The id must be a positive Long.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name is not given");
        }
        if (price == null || BigDecimal.ZERO.compareTo(price) >= 0) {
            throw new IllegalArgumentException("The price must be greater then 0");
        }

        return productCreator.create(id, name, price);
    }
}

@FunctionalInterface
interface ProductCreator<T extends Product> {
    T create(Long id, String name, BigDecimal price);
}

class ProductA extends Product {
    public ProductA(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "ProductA{}";
    }
}

class ProductB extends Product {
    public ProductB(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "ProductB{}";
    }
}


