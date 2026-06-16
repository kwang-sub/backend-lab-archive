package ch04.item17

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ComplexTest {

    @Test
    fun 생성_테스트() {
        // given

        // when
        val result = Complex.valueOf(1.0, 2.8)
        //then
        assertThat(result).isExactlyInstanceOf(Complex::class.java)
    }
}