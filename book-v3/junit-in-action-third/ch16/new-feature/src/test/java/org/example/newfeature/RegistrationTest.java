package org.example.newfeature;

import org.assertj.core.api.Assertions;
import org.example.newfeature.config.BeanConfiguration;
import org.example.newfeature.spring.Passenger;
import org.example.newfeature.spring.PassengerRegistrationEvent;
import org.example.newfeature.spring.RegistrationManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfiguration.class)
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
