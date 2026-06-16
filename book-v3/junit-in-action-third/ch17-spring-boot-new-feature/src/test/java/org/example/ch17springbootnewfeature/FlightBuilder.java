package org.example.ch17springbootnewfeature;

import org.example.ch17springbootnewfeature.model.Country;
import org.example.ch17springbootnewfeature.model.Flight;
import org.example.ch17springbootnewfeature.model.Passenger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@TestConfiguration
public class FlightBuilder {

    private static Map<String, Country> countriesMap = new HashMap();

    static {
        countriesMap.put("AU", new Country("Australia", "AU"));
        countriesMap.put("US", new Country("USA", "US"));
        countriesMap.put("UK", new Country("United Kingdom", "UK"));
    }

    @Bean
    Flight buildFlightFromCsv() throws IOException {
        Flight flight = new Flight("AA1234", 20);
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/flights_information.csv"))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] tokens = line.split(";");
                    Passenger passenger = new Passenger(tokens[0].trim());
                    passenger.setCountry(countriesMap.get(tokens[1].trim()));
                    passenger.setIsRegistered(false);
                    flight.addPassenger(passenger);
                }
            } while (line != null);
        }
        return flight;
    }

}
