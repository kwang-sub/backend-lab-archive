package ch03.atom33

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EnumTest {
    @Test
    fun levelTest() {
        assertThat(Level.Empty).isEqualTo(Level.Empty)
        assertThat(Level.Empty.ordinal).isEqualTo(4)
        assertThat(checkLevel(Level.Low)).isEqualTo("Low")
    }
}