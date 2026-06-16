package ch03.atom35

data class Simple(
    val arg1: String,
    var arg2: Int
) {
}

fun main() {
    val s1 = Simple("Hi", 29)
    val s2 = Simple("Hi", 29)
    println(s1 == s2)
}