package ch01.atom10

fun isOpen1(hour: Int) {
    val open = 9
    val closed = 20
    println("Operating hours: $open - $closed")
    val status = hour in open..closed
    println("Open: $status")
}

fun main() {
    isOpen1(6)
}