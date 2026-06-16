package ch04.atom51

fun main() {
    val naturalNumbers =
        generateSequence(1) { it + 1 }
            .toList()

    println(naturalNumbers.size)
}