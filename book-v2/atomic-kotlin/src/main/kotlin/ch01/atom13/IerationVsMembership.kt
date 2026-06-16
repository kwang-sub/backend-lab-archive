package ch01.atom13

fun main() {
    val values = 1..3
    for (v in values) {
        println("iteration $v")
    }

    val v = 2
    if (v in values) {
        println("$v is a member of $values")
    }
}