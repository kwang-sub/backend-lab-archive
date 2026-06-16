package ch04.item19

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SubTest {

    @Test
    fun 생성자_호출_테스트() {
        // given

        // when

        //then
        val sub = Sub()
        sub.overrideMe()
    }
}