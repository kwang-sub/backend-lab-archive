package com.example.java8.chap03.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public abstract class Product {
    private Long id;
    private String name;
    private BigDecimal price;
}
