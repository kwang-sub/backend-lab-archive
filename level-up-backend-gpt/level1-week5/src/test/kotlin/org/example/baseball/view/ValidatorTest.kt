package org.example.baseball.view

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.baseball.constant.ErrorMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {

    @Test
    fun validateBaseballNumber_shouldSuccess_whenValidInput() {
        // given
        val validator = Validator()
        val input = "1,2,3"
        val delimiters = ","

        // when
        validator.validateBaseballNumber(input, delimiters)

        // then
    }

    @ParameterizedTest
    @ValueSource(strings = ["0,2,3", "1,5,10"])
    fun validateBaseballNumber_shouldThrow_whenInvalidInput(
        input: String,
    ) {
        // given
        val validator = Validator()
        val delimiters = ","

        // when
        assertThatThrownBy { validator.validateBaseballNumber(input, delimiters) }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_BASEBALL_NUMBER)
    }

    @ParameterizedTest
    @ValueSource( strings =["1,1,3", "1,2,2", "1,2,1"])
    fun validateBaseballNumber_shouldThrow_whenDuplicateInput(
        input: String,
    ) {
        // given
        val validator = Validator()
        val delimiters = ","

        // when
        assertThatThrownBy { validator.validateBaseballNumber(input, delimiters) }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.DUPLICATE_BASEBALL_NUMBER)
    }

}