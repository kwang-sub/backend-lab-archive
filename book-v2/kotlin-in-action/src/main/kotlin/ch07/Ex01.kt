package ch07

import java.math.BigDecimal

fun main() {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)

//    println(p1 + p2)
    println(p1 * 2.24)

    println('a' * 3)

    println(p2)

    val list = arrayListOf(1, 2, 3)
    list += 2
    val list2 = list + 2
    println(list2)

    val p3 = -p1
    println(p3)

    var bd = BigDecimal.ZERO
    println(++bd)

    println("$p1  ${p1[0]}")

    val mutablePoint = MutablePoint(1, 2)
    mutablePoint[1] = 10
    println(mutablePoint[1])
}

operator fun Char.times(count: Int): String = this.toString().repeat(count)

operator fun BigDecimal.inc() = this + BigDecimal.ONE