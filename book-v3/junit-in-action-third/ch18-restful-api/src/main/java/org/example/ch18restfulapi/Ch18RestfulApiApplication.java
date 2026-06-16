package org.example.ch18restfulapi;

import org.example.ch18restfulapi.beans.FlightBuilder;
import org.example.ch18restfulapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootApplication
@Import(FlightBuilder.class)
public class Ch18RestfulApiApplication {

    @Autowired
    private Map<String, Country> countriesMap;

    @Autowired
    private Flight flight;

    public static void main(String[] args) {
        SpringApplication.run(Ch18RestfulApiApplication.class, args);
    }


    @Bean
    CommandLineRunner configureRepository(
            CountryRepository countryRepository,
            PassengerRepository passengerRepository,
            Flight flight) {
        return args -> {
            for (Country country : countriesMap.values()) {
                countryRepository.save(country);
            }
            for (Passenger passenger : flight.getPassengers()) {
                passengerRepository.save(passenger);
            }
        };
    }
}
