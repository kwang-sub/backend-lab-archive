package org.example.flights;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void testFlightCreation() {
        Flight flight = new Flight("AA123", 100);
        assertNotNull(flight);
    }

    @Test
    void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> {
            new Flight("AA12", 100);
        });
    }
}