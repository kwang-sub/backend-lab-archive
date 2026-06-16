package org.example.ch16traditional;

import org.assertj.core.api.Assertions;
import org.example.ch16traditional.config.BeanConfiguration;
import org.example.ch16traditional.entity.Passenger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.*;
import static org.example.ch16traditional.util.PassengerUtil.getExpectedPassenger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfiguration.class)
public class SimpleAppTest {
    @Autowired
    private Passenger expectedPassenger;

    @Test
    public void testInitPassenger() {
        Passenger passenger = getExpectedPassenger();
        assertThat(passenger).isEqualTo(expectedPassenger);
        System.out.println(expectedPassenger);
    }

}
