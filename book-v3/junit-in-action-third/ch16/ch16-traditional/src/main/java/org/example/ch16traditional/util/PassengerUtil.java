package org.example.ch16traditional.util;

import org.example.ch16traditional.entity.Country;
import org.example.ch16traditional.entity.Passenger;

public class PassengerUtil {
    public static Passenger getExpectedPassenger() {
        Passenger passenger = new Passenger("John Smith");

        Country country = new Country("USA", "US");
        passenger.setCountry(country);

        return passenger;
    }
}
