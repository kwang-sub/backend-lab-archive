package ch02.atom23

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class AverageIncomeKtTest {

    @Test
    fun test() {
        // given

        // when
        val result = averageIncome(3300, 3)
        assertThatThrownBy { averageIncome(5000, 0) }.isInstanceOf(IllegalArgumentException::class.java)
        //then
        assertThat(result).isEqualTo(1100)
//        assertThat(result2).isNull()
    }
}