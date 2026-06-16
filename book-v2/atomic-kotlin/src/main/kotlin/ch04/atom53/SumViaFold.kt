package ch04.atom53

fun main() {
    val list = listOf(1, 10, 100, 1000)
    val result = list.fold(0) { sum, n ->
        if (n == 10) return@fold sum
        sum + n
    }

    println(result)
}