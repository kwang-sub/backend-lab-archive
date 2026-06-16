package ch02

fun main() {
    println("Hello, world!")
    println(max(1, 2))

    val languages = arrayListOf("Java")
    println(languages)
    languages.add("Kotlin")
    println(languages)
}

fun max(a: Int, b: Int) = if (a > b) a else b

val question = "문자열"
val answer: Int = 42
val yearsToCompute = 7.5e6

