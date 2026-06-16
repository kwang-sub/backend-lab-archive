package ch04.atom51

import ch04.atom48.any

fun Int.isEven(): Boolean {
    println("$this.isEven()")
    return this % 2 == 0
}

fun Int.square(): Int {
    println("$this.square()")
    return this * this
}


fun Int.lessThanTen(): Boolean {
    println("$this.lessThanTen()")
    return this < 10
}

fun main() {
    val list = listOf(1, 2, 3, 4)
    val result1 = list.filter(Int::isEven)
        .map(Int::square)
        .any(Int::lessThanTen)
    println(result1)
    println("=========================")
    val result2 = list.asSequence()
        .filter(Int::isEven)
        .map(Int::square)
        .any(Int::lessThanTen)

    println(result2)
}