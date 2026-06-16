package ch03.atom36

data class Computation(
    val data: Int,
    val info: String,
    val test: Int
) {
}

fun evaluate(input: Int) =
    if (input > 5) Computation(input * 2, "High", 1)
    else Computation(input * 2, "Low", 2)

fun main() {
    val (value, description) = evaluate(1)
    println(value)
    println(description)
}