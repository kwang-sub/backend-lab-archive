package org.example.newfeature.config;

import org.example.newfeature.spring.Country;
import org.example.newfeature.spring.Passenger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.newfeature.spring")
public class BeanConfiguration {

    @Bean
    public Country beanCountry() {
        return new Country("USA", "US");
    }

    @Bean
    public Passenger beanPassenger() {
        Passenger passenger = new Passenger("John Smith");
        passenger.setCountry(beanCountry());
        passenger.setIsRegistered(false);
        return passenger;
    }
}
