package ch02.atom26

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SetTest {

    @Test
    fun setTest() {
        val intSet = setOf(1, 1, 3, 9, 9, 4)

        assertThat(intSet).isEqualTo(setOf(1, 3, 9, 4))
        assertThat(1 in intSet).isTrue()
        assertThat(2 in intSet).isFalse()

        assertThat(intSet.contains(1)).isTrue()
        assertThat(intSet.containsAll(setOf(1, 3))).isTrue()
        assertThat(intSet.union(setOf(1, 2))).isEqualTo(setOf(1, 2, 3, 9, 4))
    }
}