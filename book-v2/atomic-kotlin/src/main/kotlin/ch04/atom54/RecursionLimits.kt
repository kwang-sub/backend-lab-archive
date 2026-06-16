package ch04.atom54

private tailrec fun sum(n: Long, accumulator: Long): Long {
    if (n == 0L) return accumulator
    return sum(n - 1, accumulator + n)
}

fun main() {
    println(sum(2, 0))
    println(sum(1000, 0))
    println(sum(100_000, 0))
//    println((1..100_000L).sum())
}