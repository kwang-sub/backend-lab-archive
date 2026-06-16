package ch02.atom28

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DataTest {

    @Test
    fun test() {
        val data = Data(10)
        assertThat(data.i).isEqualTo(10)
    }
}

class Data(var i: Int)

