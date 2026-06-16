package ch07.atom79

fun Int.d1(f: (Int) -> Int) = f(this) * 10

fun Int.d2(f: Int.() -> Int) = f(this) * 10

fun f1(n: Int) = n + 3

fun Int.f2() = this + 3

fun main() {
    println(74.d1(::f1))
    println(74.d2(::f1))
    println(74.d1(Int::f2))
    println(74.d2(Int::f2))
}