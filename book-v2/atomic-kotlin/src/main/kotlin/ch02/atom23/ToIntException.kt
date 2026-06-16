package ch02.atom23

fun erroneousCode(): Any? {
    return "1$".toIntOrNull()
}

fun main() {
    println(erroneousCode())
}