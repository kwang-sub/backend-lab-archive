package org.example.ex03;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void sumOfTwoNumbers() {
        // given
        double first = 10;
        double second = 20;
        var sut = new Calculator();

        // when
        double result = sut.sum(first, second);

        // then
        assertThat(result).isEqualTo(30);
    }
}