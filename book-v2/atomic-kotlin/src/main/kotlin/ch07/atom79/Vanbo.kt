package ch07.atom79

val va: (String, Int) -> String = { str, n -> str.repeat(n) + str.repeat(n) }

val vb: String.(Int) -> String = { this.repeat(it) + repeat(it) }

fun main() {
    println(va("kwang", 2))
    println("kwang".vb(2))
}