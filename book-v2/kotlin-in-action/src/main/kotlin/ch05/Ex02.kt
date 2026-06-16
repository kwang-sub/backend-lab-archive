package ch05

fun main() {
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2))

    run { println("kk") }
}