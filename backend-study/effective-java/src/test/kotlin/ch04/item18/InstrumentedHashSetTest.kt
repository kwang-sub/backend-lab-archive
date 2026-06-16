package ch04.item18

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InstrumentedHashSetTest {

    @Test
    fun 동작_테스트() {
        // given
        val s = InstrumentedHashSet<String>()
        // when
        s.addAll(listOf("a", "b", "c"))
        //then
        assertThat(s.getAddCount()).isNotEqualTo(s.size)
    }
}