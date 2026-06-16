package ch04.atom51

import ch04.atom48.any

fun main() {
    val list = listOf(1, 2, 3, 4)
    val result = list.filter { it % 2 == 0 }
        .map { it * it }
        .any { it < 10 }

    println(result)
}