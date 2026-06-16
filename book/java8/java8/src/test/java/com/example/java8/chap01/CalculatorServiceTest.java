package com.example.java8.chap01;

import com.example.java8.chap01.CalculatorService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CalculatorServiceTest {

    @Test
    public void calculateSubtraction() {
        Calculation calculation = new Subtraction();
        final int actual = calculation.calculate(1, 1);
        assertThat(actual).isEqualTo(0);
    }

    @Test
    public void calculateMultiplication() {
        Calculation calculation = new Multiplication();
        final int actual = calculation.calculate(1, 1);
        assertThat(actual).isEqualTo(1);
    }

    @Test
    public void calculateDivision() {
        Calculation calculation = new Division();
        final int actual = calculation.calculate(8, 4);
        assertThat(actual).isEqualTo(2);
    }

    @Test
    public void calculateAddition() {
        Calculation calculation = new Addition();
        final int actual = calculation.calculate(1, 1);
        assertThat(actual).isEqualTo(2);
    }

}