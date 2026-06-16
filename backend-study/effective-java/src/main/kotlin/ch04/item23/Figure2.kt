package ch04.item23

abstract class Figure2 {
    abstract fun area(): Double
}

class Circle(
    private val radius: Double
) : Figure2() {
    override fun area(): Double {
        return Math.PI * (radius * radius)
    }
}

class Rectangle(
    private val length: Double,
    private val width: Double,
) : Figure2() {

    override fun area(): Double {
        return length * width
    }
}