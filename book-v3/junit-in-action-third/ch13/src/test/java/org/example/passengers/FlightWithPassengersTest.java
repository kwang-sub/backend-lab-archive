package org.example.passengers;

import org.assertj.core.api.Assertions;
import org.example.flights.Flight;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class FlightWithPassengersTest {

    private Flight flight = new Flight("AA123", 1);

    @Test
    void testAddRemovePassengers() throws IOException {
        Passenger passenger = new Passenger("124-56-7890", "Michael Johnson", "US");
        assertThat(flight.addPassenger(passenger)).isTrue();
        assertThat(flight.getNumberOfPassengers()).isEqualTo(1);
        assertThat(passenger.getFlight()).isEqualTo(flight);
        assertThat(flight.removePassenger(passenger)).isTrue();
        assertThat(flight.getNumberOfPassengers()).isEqualTo(0);
        assertThat(passenger.getFlight()).isNull();
    }

    @Test
    void testNumberOfSeats() {
        Passenger passenger = new Passenger("124-56-7890", "Michael Johnson", "US");
        flight.addPassenger(passenger);
        assertThat(flight.getNumberOfPassengers()).isEqualTo(1);

        Passenger passenger2 = new Passenger("127-23-7991", "John Smith", "GB");
        assertThatThrownBy(() -> flight.addPassenger(passenger2)).isInstanceOf(RuntimeException.class);
    }

}
