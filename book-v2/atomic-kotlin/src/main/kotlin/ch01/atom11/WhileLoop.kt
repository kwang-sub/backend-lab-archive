package ch01.atom11

fun condition(i: Int) = i < 100

fun main() {
    var i = 0
    do {
        println(i)
        i += 10
    } while (condition(i))
}