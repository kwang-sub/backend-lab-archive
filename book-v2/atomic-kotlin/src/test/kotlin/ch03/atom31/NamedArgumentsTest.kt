package ch03.atom31

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NamedArgumentsTest {

    @Test
    fun test() {
        assertThat(color(1, 2, 3)).isEqualTo("(1, 2, 3)")
        assertThat(color(blue = 3, red = 1, green = 2)).isEqualTo("(1, 2, 3)")
        assertThat(colorV2(1)).isEqualTo("(1, 0, 0)")
        assertThat(colorV2(blue = 1)).isEqualTo("(0, 0, 1)")
        assertThat(Color(1, 2, 3).toString()).isEqualTo("(1, 2, 3)")
    }
}

fun color(red: Int, green: Int, blue: Int) = "($red, $green, $blue)"

fun colorV2(red: Int = 0, green: Int = 0, blue: Int = 0) = "($red, $green, $blue)"