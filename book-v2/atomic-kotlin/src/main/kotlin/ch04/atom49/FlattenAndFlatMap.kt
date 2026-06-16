package ch04.atom49

fun main() {
    val intRange = 1..3

    val pare = intRange.flatMap { a -> intRange.map { b -> a to b } }

    println(pare)
}