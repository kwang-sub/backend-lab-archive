package ch06.ex

import java.awt.Point

interface Shape {
    val topLeft: Point?
    val side: Double?

    fun area(): Double = side?.let { it * it } ?: 0.0
}

class SquareV2(
    override val topLeft: Point,
    override val side: Double,
) : Shape

class RectangleV2(
    override val topLeft: Point,
    override val side: Double? = null,
    val height: Double,
    val width: Double,
) : Shape {
    override fun area(): Double = height * width
}


class CircleV2(
    override val topLeft: Point? = null,
    override val side: Double? = null,
    val center: Point,
    val radius: Double,
) : Shape {
    companion object {
        val PI = 3.14
    }
    override fun area(): Double = PI * radius * radius
}
