package ch03.atom41

fun <T> identity(arg: T): T = arg

fun main() {
    println(identity("Yellow"))
    println(identity(1))
}