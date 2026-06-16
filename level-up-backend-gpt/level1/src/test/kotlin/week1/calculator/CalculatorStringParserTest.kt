package week1.calculator

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import week1.calculator.CalculatorStringParser
import kotlin.test.Test

class CalculatorStringParserTest {

    private lateinit var calculatorStringParser: CalculatorStringParser

    @BeforeEach
    fun setUp() {
        calculatorStringParser = CalculatorStringParser()
    }

    @Test
    fun 공백_기준으로_문자열이_나눠져야한다() {
        // given
        val input = "1 + 2 - 3 * 4 / 5"
        val expected = listOf("1", "+", "2", "-", "3", "*", "4", "/", "5")

        // when
        val result = calculatorStringParser.parse(input)

        // then
        assertThat(result.size).isEqualTo(9)
        assertThat(result).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "1 + 2 - 3 * 4 / 5 +",
            "1 +",
            "+ 2", "1 2 +",
            "",
            "1 , 2, 3",
            "s + q / 1",
        ]
    )
    fun 잘못된_입력_예외처리(input: String) {
        // when
        assertThatThrownBy { calculatorStringParser.parse(input) }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("잘못된 입력입니다.")
    }
}