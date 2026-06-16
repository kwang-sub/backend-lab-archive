package ch03

import strings.join
import strings.joinToString

fun main() {
    val list = listOf(1, 2, 3)
    println(list.joinToString())
    println(list.joinToString(separator = "; "))

    val stringList = listOf("1", "2", "3")
    println(stringList.join())
}