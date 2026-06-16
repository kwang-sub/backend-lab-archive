package ch05.atom63

open class Base {
    open fun f() = "Base.f()"
}

class Derived : Base() {
    override fun f(): String {
        return "Derived.f()"
    }
}

fun Base.g() = "Base.g()"

fun Derived.g() = "Derived.g()"

fun useBase(b: Base) {
    println(b::class.simpleName)
    println(b.f())
    println(b.g())
}

fun main() {
    useBase(Base())
    useBase(Derived())
}