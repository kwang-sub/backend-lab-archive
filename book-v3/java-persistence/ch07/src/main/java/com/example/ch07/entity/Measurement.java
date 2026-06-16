package com.example.ch07.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Measurement {
    private String name;

    private String symbol;
}
