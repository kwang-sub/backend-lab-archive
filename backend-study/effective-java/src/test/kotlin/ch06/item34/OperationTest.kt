package ch06.item34

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OperationTest {

    @Test
    fun 연산_테스트() {
        // given
        val x = 10.0
        val y = 5.0
        // when
        val plus = Operation.PLUS.apply(x, y)
        val minus = Operation.MINUS.apply(x, y)
        val times = Operation.TIMES.apply(x, y)
        val divide = Operation.DIVIDE.apply(x, y)

        val fromStringPlus = Operation.fromString("+")
        // then
        assertThat(plus).isEqualTo(x + y)
        assertThat(minus).isEqualTo(x - y)
        assertThat(times).isEqualTo(x * y)
        assertThat(divide).isEqualTo(x / y)

        assertThat(Operation.PLUS.toString()).isEqualTo("+")
        assertThat(Operation.MINUS.toString()).isEqualTo("-")
        assertThat(Operation.TIMES.toString()).isEqualTo("*")
        assertThat(Operation.DIVIDE.toString()).isEqualTo("/")

        assertThat(fromStringPlus).isPresent
        assertThat(fromStringPlus.get()).isEqualTo(Operation.PLUS)
    }
}