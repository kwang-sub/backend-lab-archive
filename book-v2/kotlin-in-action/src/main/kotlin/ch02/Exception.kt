package ch02

import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

fun main() {
    check(1)
//    check(101)

    val reader = BufferedReader(StringReader("239a"))
    readNumber(reader)
}

fun check(percentage: Int) {
    if (percentage !in 0..100) throw IllegalArgumentException("예외 던짐")
    else println(percentage)
}

fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}

