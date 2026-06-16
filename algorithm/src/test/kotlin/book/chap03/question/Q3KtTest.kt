package book.chap03.question

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Q3KtTest {

    @Test
    fun searchIdxTest() {
        val list = listOf(1, 23, 4121, 1, 142, 12, 123)
        val mutableList = mutableListOf<Int>()
        val result = searchIdx(list, list.size, 1, mutableList)

        assertThat(result).isEqualTo(2)
        assertThat(mutableList).isEqualTo(mutableListOf(0, 3))
    }

    @Test
    fun binSearchXTest() {
        val list = listOf(4, 2, 1, 1, 142, 12, 123)
        val result = binSearchX(list, 1)
        assertThat(result).isEqualTo(2)
    }

    @Test
    fun binSearchTest() {
        val list = listOf(4, 2, 1, 1, 142, 12, 123)
        val result = list.binarySearch(1)
        assertThat(result).isEqualTo(3)
    }
}