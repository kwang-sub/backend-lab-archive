package book.chap04

import book.chap04.IntStack.EmptyIntStackException
import book.chap04.IntStack.OverflowIntStackException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class IntStackKtTest {

    private lateinit var intStack: IntStack

    @BeforeEach
    fun init() {
        intStack = IntStack(10)
    }

    @Test
    fun basicTest() {
        val size = intStack.size()
        val capacity = intStack.capacity()

        assertThat(size).isEqualTo(0)
        assertThat(capacity).isEqualTo(10)
    }

    @Test
    fun pushTest() {
        // given
        intStack.push(10)
        intStack.push(1651)
        intStack.push(12)
        intStack.push(1122)
        intStack.push(1512)
        intStack.push(1162)
        intStack.push(1216)
        intStack.push(11232)
        intStack.push(12145)
        intStack.push(112412)
        // when
        val size = intStack.size()
        val peek = intStack.peek()
        val full = intStack.isFull
        val empty = intStack.isEmpty

        // then
        assertThat(size).isEqualTo(10)
        assertThat(peek).isEqualTo(112412)
        assertThat(full).isEqualTo(true)
        assertThat(empty).isEqualTo(false)
        assertThatThrownBy { intStack.push(10123) }.isInstanceOf(OverflowIntStackException::class.java)
    }

    @Test
    fun popTest() {
        // given
        intStack.push(10)
        // when
        val pop = intStack.pop()
        val size = intStack.size()
        // then
        assertThat(pop).isEqualTo(10)
        assertThat(size).isEqualTo(0)
        assertThatThrownBy { intStack.pop() }.isInstanceOf(EmptyIntStackException::class.java)
    }

    @Test
    fun dumbTest() {
        intStack.push(10)
        intStack.push(1651)
        intStack.push(12)
        intStack.push(1122)
        intStack.push(1512)
        intStack.push(1162)
        intStack.push(1216)
        intStack.push(11232)
        intStack.push(12145)
        intStack.push(112412)

        intStack.dump()
    }
}