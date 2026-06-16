package ch02.atom28

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Default {
    var i: Int = 0
        get() {
            println("get()")
            return field
        }
        set(value) {
            println("set($value)")
            field = value
        }
}

class DefaultTest{
    @Test
    fun test() {
        val d = Default()
        d.i = 10
        assertThat(d.i).isEqualTo(10)
    }
}