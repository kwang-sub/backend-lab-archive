package ch06.ex

import java.awt.Point

class Square(
    val topLeft: Point,
    val side: Double,
)

class Rectangle(
    val topLeft: Point,
    val height: Double,
    val width: Double,
)

class Circle(
    val center: Point,
    val radius: Double,
)

class Geometry {
    companion object {
        val PI = 3.14
    }

    fun <Type> area(shape: Type) {
        when (shape) {
            is Square -> shape.side * shape.side
            is Rectangle -> shape.height * shape.width
            is Circle -> PI * shape.radius * shape.radius
            else -> throw NoSuchShapeException("not match shape type $shape")
        }
    }
}

class NoSuchShapeException(message: String) : RuntimeException(message)

fun main() {
    val geometry = Geometry()
    geometry.area(String)
}