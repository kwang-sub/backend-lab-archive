package ch07.atom82

data class Num(val n: Int)

infix operator fun Num.plus(rval: Num) = Num(n + rval.n)

fun main() {
    val num = Num(4) + Num(5)
    val num2 = Num(4).plus(Num(5))
    val num3 = Num(4) plus (Num(5))

    println(num)
    println(num2)
    println(num3)

}