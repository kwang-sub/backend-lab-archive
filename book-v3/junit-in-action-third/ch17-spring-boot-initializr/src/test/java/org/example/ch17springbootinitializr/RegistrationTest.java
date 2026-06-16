package org.example.ch17springbootinitializr;

import org.example.ch17springbootinitializr.spring.Passenger;
import org.example.ch17springbootinitializr.spring.PassengerRegistrationEvent;
import org.example.ch17springbootinitializr.spring.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(BeanConfiguration.class)
public class RegistrationTest {

    @Autowired
    private Passenger passenger;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testPersonRegistration() {
        registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));
        System.out.println("After registering: ");
        System.out.println(passenger);
        assertThat(passenger.isRegistered()).isTrue();
    }
}
