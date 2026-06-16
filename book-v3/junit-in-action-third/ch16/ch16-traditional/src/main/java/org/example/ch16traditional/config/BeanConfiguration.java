package org.example.ch16traditional.config;

import org.example.ch16traditional.entity.Country;
import org.example.ch16traditional.entity.Passenger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public Country beanCountry() {
        return new Country("USA", "US");
    }

    @Bean
    public Passenger beanPassenger() {
        Passenger passenger = new Passenger("John Smith");
        passenger.setCountry(beanCountry());
        return passenger;
    }
}
