package ch04.atom54

fun fibonacci(n: Long): Long {
    tailrec fun fibonacci(
        n: Int,
        current: Long,
        next: Long
    ): Long {
        if (n == 0) return current
        return fibonacci(n - 1, next, current + next)
    }
    return fibonacci(n.toInt(), 0L, 1L)
}

fun main() {
    println(fibonacci(0))
    println(fibonacci(22))
}