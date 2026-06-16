package ch04.item16

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class TimeTest {

    @Test
    fun 정상_생성_테스트() {
        // given
        // when
        val result = Time(1, 19)

        //then
        assertThat(result.hour).isEqualTo(1)
        assertThat(result.minute).isEqualTo(19)
    }

    @Test
    fun 시간_생성_예외_테스트() {
        // given

        // when
        assertThatThrownBy { Time(-1, 10) }
            .isInstanceOf(IllegalArgumentException::class.java)
        //then
    }
}