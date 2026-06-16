package org.example.baseball.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.baseball.constant.ErrorMessage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BaseballResultTest {

    @Test
    fun construct_shouldSuccess_whenValidInput() {
        // given
        val strike = 1
        val ball = 2

        // when
        val baseballResult = BaseballResult(strike, ball)

        // then
        assertEquals(strike, baseballResult.strikeCount)
        assertEquals(ball, baseballResult.ballCount)
    }

    @Test
    fun construct_shouldThrow_whenInValidStrikeCount() {
        // given
        val strike = 4
        val ball = 0

        // when
        assertThatThrownBy{BaseballResult(strike, ball)}

        // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_BASEBALL_RESULT_COUNT)
    }

    @Test
    fun construct_shouldThrow_whenInValidBallCount() {
        // given
        val strike = 0
        val ball = 4

        // when
        assertThatThrownBy{BaseballResult(strike, ball)}

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_BASEBALL_RESULT_COUNT)
    }

    @Test
    fun construct_shouldThrow_whenInValidSumBallStrikeCount() {
        // given
        val strike = 2
        val ball = 2

        // when
        assertThatThrownBy{BaseballResult(strike, ball)}

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_BASEBALL_RESULT_COUNT)
    }
}