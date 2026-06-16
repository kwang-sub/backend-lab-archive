package book.chap01.question

import book.chap01.question.gaussianAddition
import book.chap01.question.max
import book.chap01.question.min
import book.chap01.question.plusPrint
import book.chap01.question.squarePrint
import book.chap01.question.sumOf
import book.chap01.question.triangleLB
import book.chap01.question.triangleLU
import book.chap01.question.triangleRB
import book.chap01.question.triangleRU
import book.chap01.question.unitCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Q1Test {

    @Test
    fun max4Test() {
        val result = max(1, 2, 3, 4)
        assertThat(result).isEqualTo(4)
    }

    @Test
    fun min3Test() {
        val result = min(1, 2, 3)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun plusPrintTest() {
        plusPrint(5)
    }

    @Test
    fun gaussianAdditionTest() {
        val result = gaussianAddition(10)
        assertThat(result).isEqualTo(55)

        val result2 = gaussianAddition(5)
        assertThat(result2).isEqualTo(15)

        val result3 = gaussianAddition(3)
        assertThat(result3).isEqualTo(6)
    }

    @Test
    fun sumOfTest() {
        val result = sumOf(3, 5)
        assertThat(result).isEqualTo(12)

        val result2 = sumOf(6, 4)
        assertThat(result2).isEqualTo(15)
    }

    @Test
    fun unitCountTest() {
        val result = unitCount(10)
        assertThat(result).isEqualTo(2)

        val result2 = unitCount(1)
        assertThat(result2).isEqualTo(1)
    }

    @Test
    fun squarePrintTest() {
        squarePrint(5)
    }

    @Test
    fun triangleLBTest() {
        triangleLB(4)
    }

    @Test
    fun triangleLUTest() {
        triangleLU(4)
    }

    @Test
    fun triangleRUTest() {
        triangleRU(4)
    }

    @Test
    fun triangleRBTest() {
        triangleRB(4)
    }
    @Test
    fun spira() {
        book.chap01.question.spira(4)
    }

    @Test
    fun npira() {
        book.chap01.question.npira(4)
    }
}