package ch04.item23

class Figure(
    val shape: Shape
) {

    var length: Double? = null
    var width: Double? = null

    var radius: Double? = null

    enum class Shape{
        RECTANGLE, CIRCLE
    }

    constructor(radius: Double): this(Shape.CIRCLE) {
        this.radius = radius
    }

    constructor(length: Double, width: Double) : this(Shape.RECTANGLE) {
        this.length = length
        this.width = width
    }

    fun area(): Double {
        return when (shape) {
            Shape.RECTANGLE -> length!! * width!!
            Shape.CIRCLE -> Math.PI * (radius!! * radius!!)
        }
    }
}