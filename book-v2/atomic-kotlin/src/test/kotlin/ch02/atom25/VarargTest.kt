package ch02.atom25

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class VarargTest {

    @Test
    fun listOfTest() {
        assertThat(listOf(1)).isEqualTo(listOf(1))
    }

    @Test
    fun varargSumTest() {
        assertThat(sum(13, 27, 44)).isEqualTo(84)
        assertThat(sum()).isEqualTo(0)
    }

    @Test
    fun varargLikeTest() {
        assertThat(evaluate(10,-3, 8, 1, 9)).isEqualTo("Size: 5 Sum: 25 Average: 5.0")
    }

    @Test
    fun spreadOperatorTest() {
        val array = intArrayOf(4, 5)
        assertThat(sum(1, 2, 3, *array, 6)).isEqualTo(21)
    }
}

fun sum(vararg numbers: Int): Int {
    var total = 0
    for (n in numbers) {
        total += n
    }
    return total
}

fun evaluate(vararg ints: Int) = "Size: ${ints.size} Sum: ${ints.sum()} Average: ${ints.average()}"