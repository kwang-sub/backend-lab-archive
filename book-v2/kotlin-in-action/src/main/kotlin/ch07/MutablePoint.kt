package ch07

data class MutablePoint(
    var x: Int,
    var y: Int,
) {
    operator fun set(index: Int, value: Int) {
        when(index) {
            0 -> x = value
            1 -> y = value
            else -> throw IndexOutOfBoundsException()
        }
    }

    operator fun get(index: Int): Int {
        return when(index) {
            0 -> x
            1 -> y
            else -> throw IndexOutOfBoundsException()
        }
    }
}