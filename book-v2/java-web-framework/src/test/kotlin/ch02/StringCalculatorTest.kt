package ch02

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StringCalculatorTest {


    private lateinit var stringCalculator: StringCalculator

    @BeforeEach
    fun init() {
        stringCalculator = StringCalculator()
    }

    @Test
    fun 스트링_문자열클래스() {
        assertThat(stringCalculator).isNotNull()
    }

    @Test
    fun 빈문자열() {
        val sum = stringCalculator.add("")
        assertThat(sum).isEqualTo(0)
    }

    @Test
    fun 한개() {
        val sum = stringCalculator.add("1")
        assertThat(sum).isEqualTo(1)
    }

    @Test
    fun 구분자_기준으로_합() {
        val str = "1,2,3"
        val sum = stringCalculator.add(str)
        assertThat(sum).isEqualTo(6)
    }

    @Test
    fun 커스텀_구분자() {
        val str = "//!\n1!2!3"
        val sum = stringCalculator.add(str)
        assertThat(sum).isEqualTo(6)
    }

    @Test
    fun 음수_문자_일_경우_예외() {
        val str = "-1,2,3"
        assertThatThrownBy { stringCalculator.add(str) }
            .isExactlyInstanceOf(RuntimeException::class.java)
    }
}