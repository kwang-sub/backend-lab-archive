package ch03.atom40

fun main() {
    val s1: String? = null
    println(s1.isNullOrEmpty())
    println(s1.isNullOrBlank())

    val s2: String = " "
    println(s2.isNullOrEmpty())
    println(s2.isNullOrBlank())
}