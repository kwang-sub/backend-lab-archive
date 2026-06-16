package week1.calculator

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import week1.calculator.Operator

class OperatorTest {

    @Test
    fun `덧셈 연산`() {
        // given
        val operator = Operator.ADD

        // when
        val result = operator.operation(1, 2)

        // then
        assertThat(result).isEqualTo(3)
        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `뺄셈 연산`() {
        // given
        val operator = Operator.SUB

        // when
        val result = operator.operation(1, 2)

        // then
        assertThat(result).isEqualTo(-1)
    }

    @Test
    fun `곱셈 연산`() {
        // given
        val operator = Operator.MUL

        // when
        val result = operator.operation(7, 2)

        // then
        assertThat(result).isEqualTo(14)
    }

    @Test
    fun `나눗셈 연산`() {
        // given
        val operator = Operator.DIV

        // when
        val result = operator.operation(10, 2)

        // then
        assertThat(result).isEqualTo(5)
    }

    @Test
    fun `0으로 나눗셈 연산 예외 발생`() {
        // given
        val operator = Operator.DIV

        // when
        Assertions.assertThatThrownBy { operator.operation(10, 0) }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("0으로 나눌 수 없습니다.")
    }
}