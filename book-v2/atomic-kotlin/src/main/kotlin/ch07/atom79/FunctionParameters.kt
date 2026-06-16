package ch07.atom79

class A {
    fun af() = 1
}

class B {
    fun bf() = 2
}

fun f1(lambda: (A, B) -> Int) = lambda(A(), B())

fun f2(lambda: A.(B) -> Int) = A().lambda(B())

fun main() {
    val f1 = f1 { aa, bb -> aa.af() + bb.bf() }
    println(f1)
    val f2 = f2 { this.af() + it.bf() }
    println(f2)
}