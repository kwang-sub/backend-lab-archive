package org.example.baseball.domain

import org.assertj.core.api.Assertions.*
import org.example.baseball.constant.ErrorMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BaseballNumberTest {


    @Test
    fun construct_shouldSuccess_whenBetween1And9() {
        // given
        val number1: Int = 1
        val number2: Int = 3
        val number3: Int = 9

        // when
        val baseBallNumber = BaseballNumber(number1, number2, number3)

        // then
        assertThat(baseBallNumber).isNotNull
        assertThat(baseBallNumber.numbers).isEqualTo(listOf(number1, number2, number3))
    }

    @ParameterizedTest
    @CsvSource("0, 1, 9", "1, 5, 10")
    fun construct_shouldThrow_whenNotBetween1And9(number1: Int, number2: Int, number3: Int) {
        // given

        // when
        assertThatThrownBy { BaseballNumber(number1, number2, number3) }
        // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_BASEBALL_NUMBER)
    }

    @ParameterizedTest
    @CsvSource("1, 1, 9", "1, 5, 5", "1, 5, 1")
    fun construct_shouldThrow_whenDuplicateNumber(number1: Int, number2: Int, number3: Int) {
        // given

        // when
        assertThatThrownBy { BaseballNumber(number1, number2, number3) }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.DUPLICATE_BASEBALL_NUMBER)
    }
}

