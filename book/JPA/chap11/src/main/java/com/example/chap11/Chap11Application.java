package com.example.chap11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class Chap11Application {

    public static void main(String[] args) {
        SpringApplication.run(Chap11Application.class, args);
    }

}
