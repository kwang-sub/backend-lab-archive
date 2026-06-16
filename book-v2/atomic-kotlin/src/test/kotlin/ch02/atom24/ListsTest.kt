package ch02.atom24

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class ListsTest {
    @Test
    fun listTest() {
        // given
        val ints = listOf(99, 3, 5, 7, 11, 13)

        // when
        var result = ""
        for (i in ints) {
            result += "$i "
        }

        //then
        assertThat(ints).isEqualTo(listOf(99, 3, 5, 7, 11, 13))
        assertThat(result).isEqualTo("99 3 5 7 11 13 ")
    }

    @Test
    fun outOfBoundsTest() {
        val ints = listOf(1, 2, 3)
        assertThatThrownBy { ints[3] }.isInstanceOf(ArrayIndexOutOfBoundsException::class.java)
    }

    @Test
    fun listUsefulFunctionTest() {
        val doubles = listOf(1.1, 2.2, 3.3, 4.4)
        val strings = listOf("Twas", "Brillig", "And", "Slithy", "Toves")


        assertThat(doubles.sum()).isEqualTo(11.0)
        assertThat(strings).isEqualTo(listOf("Twas", "Brillig", "And", "Slithy", "Toves"))
        assertThat(strings.sorted()).isEqualTo(listOf("And", "Brillig", "Slithy", "Toves", "Twas"))
        assertThat(strings.first()).isEqualTo("Twas")
        assertThat(strings.takeLast(2)).isEqualTo(listOf("Slithy", "Toves"))
    }

    @Test
    fun parameterizedTypesTest() {
        val numbers = listOf(1, 2, 3)
        val strings = listOf("one", "two", "three")

        val numbers2: List<Int> = listOf(1, 2, 3)
        val strings2: List<String> = listOf("one", "two", "three")

        assertThat(numbers).isEqualTo(numbers2)
        assertThat(strings).isEqualTo(strings2)
    }

    @Test
    fun parameterizedReturnTest() {
        assertThat(inferred('a', 'b')).isEqualTo(listOf('a', 'b'))
    }

    @Test
    fun mutableListTest() {
        val list = mutableListOf<Int>()
        list.add(1)
        list.addAll(listOf(2, 3))

        list += 4
        list += listOf(5, 6)

        assertThat(list).isEqualTo(listOf(1, 2, 3, 4, 5, 6))
    }

    @Test
    fun apparentlyMutableList() {
        var list = listOf('X')
        list += 'Y'

        assertThat(list).isEqualTo(listOf('X', 'Y'))
    }
}

fun inferred(p: Char, q: Char) = listOf(p, q)

fun explicit(p: Char, q: Char): List<Char> = listOf(p, q)