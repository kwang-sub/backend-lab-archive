package org.example.baseball.factory

import org.assertj.core.api.Assertions
import org.example.baseball.domain.BaseballNumber
import org.junit.jupiter.api.Test

class BaseballNumberGeneratorTest {

    @Test
    fun generate_shouldSuccess_whenGenerateRandomNumber() {
        // given
        val baseBallNumberGenerator = BaseballNumberGenerator()
        // when
        val baseBallNumber = baseBallNumberGenerator.generate()
        // then
        Assertions.assertThat(baseBallNumber).isInstanceOf(BaseballNumber::class.java)
    }
}