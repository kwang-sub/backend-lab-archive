package ch05

fun alphabet(): String =
    with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I konow the alphabet!")
        toString()
    }

fun alphaber2(): String =
    StringBuilder()
        .apply {
            for (letter in 'A'..'Z') {
                append(letter)
            }
            append("\nNow I konow the alphabet!")
        }.toString()

fun main() {
    println(alphabet())
    println("=============")
    println(alphaber2())
}