package ch04.item18

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.TreeSet

class InstrumentedSetTest {

    @Test
    fun 생성_테스트() {
        // given
        val set = InstrumentedSet<String>()
        val param = listOf("123", "1")
        // when
        set.addAll(param)
        //then
        assertThat(set.getAddCount()).isEqualTo(param.size)
    }

    @Test
    fun 다양한_set_테스트() {
        // given
        val hash = InstrumentedSet<String>(HashSet())
        val tree = InstrumentedSet<String>(TreeSet())
        // when

        //then
        assertThat(hash.set).isExactlyInstanceOf(java.util.HashSet::class.java)
        assertThat(tree.set).isExactlyInstanceOf(java.util.TreeSet::class.java)
    }
}