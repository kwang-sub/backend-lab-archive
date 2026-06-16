package ch02

import ch02.Color.*
import geometry.shapes.createRandomRectangle

fun main() {
    val person  = Person("Bob", true)
    println(person.name)
    println(person.isMarried)
    println()

    val rectangle = createRandomRectangle()
    println(rectangle.isSquare)
    println()

    println(INDIGO.rgb())
    println(getMnemonic(VIOLET))
    println()

    println(mix(BLUE, YELLOW))
    println(mix(YELLOW, BLUE))
    println()

    println(mixOptimized(BLUE, YELLOW))
    println(mixOptimized(BLUE, BLUE))
}