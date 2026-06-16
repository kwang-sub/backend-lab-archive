package ch04.atom48

val isPlus: (Int) -> Boolean = { it > 0 }

fun main() {
    val result = listOf(1, -2, 3).filter(isPlus)
    println(result)
}