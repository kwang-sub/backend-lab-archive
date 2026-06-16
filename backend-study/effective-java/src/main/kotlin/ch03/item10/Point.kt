package ch03.item10

import java.awt.Color
import java.util.concurrent.atomic.AtomicInteger

open class Point(
    private val x: Int,
    private val y: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Point) return false
        return other.x == x && other.y == y
    }
}

class ColorPoint(
    x: Int,
    y: Int,
    private val color: Color
) : Point(x, y) {

    override fun equals(other: Any?): Boolean {
        if (other !is ColorPoint) return false
        return super.equals(other) && other.color == color
    }
}

class ColorPointV2 private constructor(
    private val color: Color,
    private val point: Point,
) {
    constructor(x: Int, y: Int, color: Color) : this(color, Point(x, y))

    fun asPoint(): Point = point

    override fun equals(other: Any?): Boolean {
        if (other !is ColorPointV2) return false
        return other.point == point && other.color == color
    }
}

class CounterPoint(
    x: Int,
    y: Int
) : Point(x, y) {
    init {
        counter.incrementAndGet()
    }

    companion object {
        val counter: AtomicInteger = AtomicInteger()
    }
}

fun main() {
    val p1 = ColorPoint(1, 2, Color.pink)
    val p2 = Point(1, 2)
    val p3 = ColorPoint(1, 2, Color.blue)
    val p4 = CounterPoint(1, 2)
    val p5 = ColorPointV2(1, 2, Color.black)
    val p6 = ColorPointV2(1, 2, Color.black)
    println(p1 == p2)
    println(p2 == p3)
    println(p1 == p3)
    println(p2 == p4)
    println(p6 == p5)
}

val unitCircle = mutableSetOf(
    Point(1, 0),
    Point(0, 1),
    Point(-1, 0),
    Point(0, -1),
)

fun onUnitCircle(p: Point): Boolean {
    return unitCircle.contains(p)
}