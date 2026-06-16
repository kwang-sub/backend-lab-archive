package ch03.atom33

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class GermanOrdinalsTest {
    @Test
    fun test() {
        assertThat(ordinal(1)).isEqualTo("erste")
        assertThat(ordinal(2)).isEqualTo("dritte")
        assertThat(ordinal(3)).isEqualTo("dreite")
        assertThatThrownBy { ordinal(4) }.isInstanceOf(NoSuchElementException::class.java)
    }
}

val numbers = mapOf(
    1 to "eins", 2 to "zwei", 3 to "drei"
)

fun ordinal(i: Int): String =
    when (i) {
        1 -> "erste"
        2 -> "dritte"
        else -> numbers.getValue(i) + "te"
    }