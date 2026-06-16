package ch07

data class Rectangle(
    val upperLeft: Point,
    val lowerRight: Point,
) {
    operator fun contains(p: Point): Boolean {
        return p.x in upperLeft.x until lowerRight.x && p.y in upperLeft.y until lowerRight.y
    }

}