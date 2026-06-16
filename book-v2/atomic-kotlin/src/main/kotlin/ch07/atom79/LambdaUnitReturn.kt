package ch07.atom79

fun unitReturn(lambda: A.() -> Unit) = A().lambda()

fun nonUnitReturn(lambda: A.() -> String) = A().lambda()

fun main() {
    println(unitReturn { "test" })
    println(nonUnitReturn { "test" })
}