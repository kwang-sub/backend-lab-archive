package ch02.atom28

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HamstersTest {

    @Test
    fun test() {
        val cage = Cage(2)
        assertThat(cage.full).isFalse()
        assertThat(cage.capacity).isEqualTo(2)

        assertThat(cage.put(Hamster("Alice"))).isTrue()
        assertThat(cage.put(Hamster("Bob"))).isTrue()
        assertThat(cage.full).isTrue()
        assertThat(cage.put(Hamster("Alice"))).isFalse()

        cage.take()
        assertThat(cage.full).isFalse()
    }
}

class Hamster(val name: String)

class Cage(private val maxCapacity: Int) {
    private val hamsters = mutableListOf<Hamster>()
    val capacity: Int
        get() = maxCapacity - hamsters.size
    val full: Boolean
        get() = hamsters.size == maxCapacity

    fun put(hamster: Hamster): Boolean =
        if (full) false
        else {
            hamsters += hamster
            true
        }

    fun take(): Hamster = hamsters.removeAt(0)

}