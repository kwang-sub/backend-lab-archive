package org.example.ch16traditional;

import org.example.ch16traditional.entity.Country;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Ch16TraditionalApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Ch16TraditionalApplication.class, args);
        Country bean = context.getBean(Country.class);
        System.out.println(bean);

    }

}
