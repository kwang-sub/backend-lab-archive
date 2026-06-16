package org.example.baseball.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class JudgeTest {

    @ParameterizedTest
    @CsvSource("1, 2, 3, 3, 0", "4, 5, 6, 0, 0", "3, 5, 1, 0, 2")
    fun judge_shouldSuccess_whenMatchScore(
        number1: Int,
        number2: Int,
        number3: Int,
        expectedStrike: Int,
        expectedBall: Int
    ) {
        // given
        val baseBallNumber = BaseballNumber(1, 2, 3)
        val userInputNumber = BaseballNumber(number1, number2, number3)

        val judge = Judge()
        // when
        val result = judge.judge(baseBallNumber, userInputNumber)

        // then
        Assertions.assertThat(result.strikeCount).isEqualTo(expectedStrike)
        Assertions.assertThat(result.ballCount).isEqualTo(expectedBall)
    }
  
}

