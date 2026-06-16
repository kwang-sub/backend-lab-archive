package org.example.runners;

import org.example.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(CustomTestRunner.class)
public class CustomTestRunnerTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        System.out.println("test Add");
        double result = calculator.add(10, 50);
        assertThat(result).isEqualTo(60);
    }
}