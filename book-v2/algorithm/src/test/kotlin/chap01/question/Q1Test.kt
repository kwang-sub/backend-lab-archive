package chap01.question

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Q1Test {

    @Test
    fun max4Test() {
        val result = Q1.max(1, 2, 3, 4)
        assertThat(result).isEqualTo(4)
    }

    @Test
    fun min3Test() {
        val result = Q1.min(1, 2, 3)
        assertThat(result).isEqualTo(1)
    }
}