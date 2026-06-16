package ch05.atom69

interface Amphibian

fun createAmphibian(): Amphibian {
    class Frog : Amphibian
    return Frog()
}

fun main() {
    val amphibian = createAmphibian()
}