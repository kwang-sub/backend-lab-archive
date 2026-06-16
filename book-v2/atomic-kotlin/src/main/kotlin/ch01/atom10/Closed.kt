package ch01.atom10

fun isClosed(hour: Int) {
    val open = 9
    val closed = 20
    println("Operating hours: $open - $closed")
    val status = hour !in open..closed
    println("Closed: $status")
}

fun main() {
    isClosed(6)
}