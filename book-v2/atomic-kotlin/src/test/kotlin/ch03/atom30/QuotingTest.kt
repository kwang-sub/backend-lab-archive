package ch03.atom30

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class QuotingTest {

    @Test
    fun test() {
        assertThat("hi".singleQuote()).isEqualTo("'hi'")
        assertThat("hi".doubleQuote()).isEqualTo("\"hi\"")
    }
}

fun String.singleQuote() = "'$this'"
fun String.doubleQuote() = "\"$this\""
fun String.strangeQuote() = this.singleQuote().singleQuote()
fun String.tooManyQuotes() = doubleQuote().doubleQuote()