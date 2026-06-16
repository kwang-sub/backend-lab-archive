package ch04.atom49

fun main() {
    val left = listOf("a", "b", "c", "d")
    val right = listOf("q", "r", "s", "t")

    val zip = left.zip(right)
    println(zip)


    val zip2 = left.zip(0..1)
    println(zip2)
}