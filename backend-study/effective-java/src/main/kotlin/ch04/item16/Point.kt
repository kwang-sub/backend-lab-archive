package ch04.item16

data class Point(
    private val _x: Double,
    private val _y: Double,
) {
    val x: Double
        get() = _x
    val y: Double
        get() = _y
}