package org.example.ch17springbootinitializr;

import org.example.ch17springbootinitializr.spring.Country;
import org.example.ch17springbootinitializr.spring.Passenger;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
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
