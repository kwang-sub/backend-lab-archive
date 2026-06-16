package week1.calculator

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.NullSource
import week1.calculator.Calculator
import week1.calculator.CalculatorStringParser
import week1.calculator.Operator

class CalculatorTest {

    private lateinit var calculator: Calculator

    @BeforeEach
    fun setUp() {
        calculator = Calculator(CalculatorStringParser())
    }

    @ParameterizedTest
    @CsvSource(
        "1, 2, +, 3",
        "2, 10, +, 12",
        "5, 2, /, 2",
        "8, 3, *, 24",
        "4, 2, -, 2",
    )
    fun `정상_동작_테스트`(num1: Int, num2: Int, operator: String, expected: Int) {
        Operator.from(operator)

        // given
        val input = "$num1 $operator $num2"

        // when
        val result = calculator.calculate(input)

        // then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `다중_연산_확인`() {
        // given
        val input = "10 / 2 + 3 * 4 - 2"

        // when
        val result = calculator.calculate(input)

        // then
        assertThat(result).isEqualTo(30)
    }

    @Test
    fun `0으로_나눗셈_연산_예외_발생`() {
        // given
        val input = "10 / 0"

        // when
        assertThatThrownBy { calculator.calculate(input) }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("0으로 나눌 수 없습니다.")
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    fun `빈_문자열_또는_null_입력시_0을_반환`(input: String?) {
        // given

        // when
        val result = calculator.calculate(input)
        // then
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `숫자_하나만_입력한_경우_숫자를_반환한다`() {
        // given
        val input = "2"
        // when
        val result = calculator.calculate(input)
        // then
        assertThat(result).isEqualTo(2)
    }

    @Test
    fun `구분자_콤마_또는_세미콜론은_합을 구해야한다`() {
        // given
        val input = "2 , 3 ; 4"
        // when
        val result = calculator.calculate(input)

        // then
        assertThat(result).isEqualTo(9)
    }

    @Test
    fun `시작부분에_구분자를_이용해서_커스텀_구분자를_제공하여_합산한다`() {
        // given
        val input = "//t\n1t2t3"
        // when
        val reuslt = calculator.calculate(input)
        // then
        assertThat(reuslt).isEqualTo(6)
    }

    @Test
    fun `음수일_경우_예외발생`() {
        // given
        val input = "-1 + 2 - 3"

        // when
        assertThatThrownBy { calculator.calculate(input) }
        // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("음수는 지원하지 않습니다.")
    }
}