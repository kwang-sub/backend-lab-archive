package book.chap05.question

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Q5KtTest {

    @Test
    fun factorialTest() {
        val factorial = factorial(3)
        assertThat(factorial).isEqualTo(6)

        val factorial1 = factorial(10)
        assertThat(factorial1).isEqualTo(3628800)
    }


    @Test
    fun gcdTest() {
        val gcd1 = gcd(3, 2)
        val gcd2 = gcd(10, 5)
        val gcd3 = gcd(18, 12)

        assertThat(gcd1).isEqualTo(1)
        assertThat(gcd2).isEqualTo(5)
        assertThat(gcd3).isEqualTo(6)
    }

    @Test
    fun gecArrayTest() {
        val gcd1 = gcdArray(intArrayOf(1, 23, 51), 0, 3)
        val gcd2 = gcdArray(intArrayOf(3, 6, 9), 0, 3)
        val gcd3 = gcdArray(intArrayOf(81, 162, 9), 0, 3)

        assertThat(gcd1).isEqualTo(1)
        assertThat(gcd2).isEqualTo(3)
        assertThat(gcd3).isEqualTo(9)
    }
}