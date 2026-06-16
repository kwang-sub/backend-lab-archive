package ch03

import strings.parsePath

fun main() {
    val strings: List<String> = listOf("a", "b", "c")
    println(strings.last())

    test("1", "2")

    println("12.345-6.A".split("\\.|-".toRegex()))
    parsePath("디렉터리/파일.확장자")
}

fun test(vararg tt: String) {
    println(listOf(*tt))
}