package ch02.item03.ch01

import ch02.item03.ch01.Elvis
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ElvisTest {
    @Test
    fun 싱글턴_테스트() {
        // given
        val elvis = Elvis.getInstance()
        // when
        val result = Elvis.getInstance()
        //then
        assertThat(result).isEqualTo(elvis)
    }
}