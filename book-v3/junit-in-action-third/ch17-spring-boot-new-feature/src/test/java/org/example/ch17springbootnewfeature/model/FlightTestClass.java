package org.example.ch17springbootnewfeature.model;

import org.example.ch17springbootnewfeature.FlightBuilder;
import org.example.ch17springbootnewfeature.registration.PassengerRegistrationEvent;
import org.example.ch17springbootnewfeature.registration.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(FlightBuilder.class)
class FlightTestClass {

    @Autowired
    private Flight flight;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testFlightPassengersRegistration() {
        for (Passenger passenger : flight.getPassengers()) {
            assertThat(passenger.isRegistered()).isFalse();
            registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));
        }
        System.out.println("모든 승객이 등록되었는지 확인");

        for (Passenger passenger : flight.getPassengers()) {
            assertThat(passenger.isRegistered()).isTrue();
        }
    }

}