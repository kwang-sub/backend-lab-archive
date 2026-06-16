package ex01

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScoreCollectionTest {

    @Test
    fun test() {
        val collection = ScoreCollection()
        collection.add { 5 }
        collection.add { 7 }

        val result = collection.arithmeticMean()
        assertEquals(result, 6)
    }

    @Test
    fun test2() {
        val collection = ScoreCollection()
        assertThrows(ArithmeticException::class.java) { collection.arithmeticMean() }
    }
}
