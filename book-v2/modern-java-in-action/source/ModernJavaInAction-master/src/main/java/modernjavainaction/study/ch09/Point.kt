package modernjavainaction.study.ch09

class Point(
    private val x: Int,
    private val y: Int,
) {
    fun getX() = x

    fun getY() = y

    fun moveRightBy(x: Int): Point {
        return Point(this.x + x, this.y)
    }

    companion object {
        fun compareByXAndThenY(): Comparator<Point> = Comparator.comparing(Point::getX).thenComparing(Point::getY)
    }
}