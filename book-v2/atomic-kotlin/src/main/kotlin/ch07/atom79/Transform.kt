package ch07.atom79

fun String.transform1(n: Int, lambda: (String, Int) -> String) = lambda(this, n)

fun String.transform2(n: Int, lambda: String.(Int) -> String) = lambda(this, n)

val duplicate: String.(Int) -> String = {
    repeat(it)
}

val alternate: String.(Int) -> String = { toCharArray().filterIndexed { i, _ -> i % it == 0 }.joinToString("") }

fun main() {
    println("hello".transform1(5, duplicate).transform2(5, alternate))
}