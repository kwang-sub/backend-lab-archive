package modernjavainaction.study.ch03

import java.awt.Color


fun main() {
    val c1 = TriFunction<Int, Int, Int, Color>(::Color)
    val color = c1.apply(1, 2, 3)
    println(color)
}


fun interface TriFunction<T, U, V, R> {
    fun apply(t: T, u: U, v: V): R
}