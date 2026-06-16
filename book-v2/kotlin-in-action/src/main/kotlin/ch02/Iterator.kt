package ch02

import java.util.*

val binaryReps = TreeMap<Char, String>()

fun main() {
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary
    }
    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }

    val list = listOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index = $element")
    }

    println(isLetter('z'))
    println(isNotDigit('1'))

    println("Kotlin" in "Java".."Scala")
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
fun recognize(c: Char) =
    when (c) {
        in '0'..'9' -> "It's a digit!"
        in 'a'..'z', in 'A'..'Z' -> "It's a letter"
        else -> "I don't know..."
    }
