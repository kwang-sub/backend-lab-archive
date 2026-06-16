package ch02.atom21

import pythagorean.RightTriangle

fun main() {
    val rt = RightTriangle(3.0, 4.0)
    println(rt.hypotenuse())
    println(rt.area())
}