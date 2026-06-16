package ch02.item06.ex01

fun main() {
    val str: String = "bikini"
    println(sum())
}

fun sum(): Long {
    var sum: Long = 0L
    for (i in 0..Int.MAX_VALUE) {
        sum += i
    }
    return sum
}