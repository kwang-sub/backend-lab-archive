package ch07.atom79

open class Base {
    open fun f() = 1
}

class Derived : Base() {
    override fun f(): Int {
        return 99
    }
}

fun Base.g() = f()

fun Base.h(xl: Base.() -> Int) = xl()

fun main() {
    val b: Base = Derived()
    println(b.g())
    println(b.h { f() })
}