package org.example.ch19hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Ch19HibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch19HibernateApplication.class, args);
    }

}
