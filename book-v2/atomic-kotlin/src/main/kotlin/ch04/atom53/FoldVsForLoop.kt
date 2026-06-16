package ch04.atom53

fun main() {
    val list = listOf(1, 10, 100, 1000)
    var accumulator = 0
    val operation = { sum: Int, i: Int -> sum + i }
    for (i in list) {
        accumulator = operation(accumulator, i)
    }

    println(accumulator)
}