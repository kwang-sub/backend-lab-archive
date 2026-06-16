package ch01.atom13

fun inFloatRange(n: Double) {
    val r = 1.0..10.0
    println("$n in $r? ${n in r}")
}

fun main() {
    inFloatRange(0.9999999999)
    inFloatRange(5.0)
    inFloatRange(10.0)
    inFloatRange(10.0000000000001)
}