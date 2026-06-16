package ch04.atom49

fun main() {
    val list = listOf(
        listOf(1, 2),
        listOf(1, 2),
        listOf(1, 2),
    )

    val result = list.flatten()
    println(result)
}