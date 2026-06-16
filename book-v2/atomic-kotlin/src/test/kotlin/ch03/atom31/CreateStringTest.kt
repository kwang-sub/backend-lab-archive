package ch03.atom31

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CreateStringTest {

    @Test
    fun test() {
        val list = listOf(1, 2, 3)
        assertThat(list.toString()).isEqualTo("[1, 2, 3]")
        assertThat(list.joinToString()).isEqualTo("1, 2, 3")
    }
}