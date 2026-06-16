package ch02.atom27

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapTest {
    @Test
    fun mapsTest() {
        val constants = mapOf("Pi" to 3.141, "e" to 2.718, "phi" to 1.618)

        assertThat(constants).isEqualTo(mapOf("Pi" to 3.141, "e" to 2.718, "phi" to 1.618))
        assertThat(constants["e"]).isEqualTo(2.718)
        assertThat(constants.keys).isEqualTo(setOf("Pi", "e", "phi"))
    }
}