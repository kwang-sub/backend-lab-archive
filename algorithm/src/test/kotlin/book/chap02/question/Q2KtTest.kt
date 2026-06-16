package book.chap02.question

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Q2KtTest {

    @Test
    fun sumOfTest() {
        val result = sumOf(intArrayOf(1, 2, 3))
        assertThat(result).isEqualTo(6)
    }

    @Test
    fun copyTest() {
        val a = IntArray(3)
        val b = intArrayOf(1, 2, 3)
        copy(a, b)
        assertThat(a).isEqualTo(b)
    }

    @Test
    fun rcopyTest() {
        val a = IntArray(3)
        val b = intArrayOf(1, 2, 3)
        rcopy(a, b)
        assertThat(a).isEqualTo(intArrayOf(3, 2, 1))
    }

    @Test
    fun cardConvTest() {
        val charArray = CharArray(32)
        val result = cardConv(8, 2, charArray)
        charArray.forEach { print("$it ") }
        assertThat(result).isEqualTo(4)
    }

    @Test
    fun cardConvPrintTest() {
        cardConvPrint(59, 2)
    }
}