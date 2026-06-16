package ch04.atom54

fun factorial(n: Long): Long {
    if (n <= 1) return 1
    return n * factorial(n - 1)
}

fun main() {
    println(factorial(5))
}