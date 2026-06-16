package ko.ch02

fun main() {
    int2DArray()
}

fun int2DArray() {
    val x = Array(2) { IntArray(4) }

    x[0][1] = 37
    x[0][3] = 54
    x[1][2] = 65

    for (i in 0 until 2) {
        for (j in 0 until 4) {
            println("x[$i][$j] = ${x[i][j]}")
        }
    }

}