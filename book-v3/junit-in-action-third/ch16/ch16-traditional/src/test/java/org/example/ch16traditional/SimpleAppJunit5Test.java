package org.example.ch16traditional;

import org.assertj.core.api.Assertions;
import org.example.ch16traditional.config.BeanConfiguration;
import org.example.ch16traditional.entity.Passenger;
import org.example.ch16traditional.util.PassengerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BeanConfiguration.class)
public class SimpleAppJunit5Test {

    @Autowired
    private Passenger passenger;
    private Passenger expectedPassenger;

    @BeforeEach
    void setUp() {
        expectedPassenger = PassengerUtil.getExpectedPassenger();
    }

    @Test
    void testInitPassenger() {
        assertThat(passenger).isNotNull();
        assertThat(passenger).isEqualTo(expectedPassenger);
    }
}
