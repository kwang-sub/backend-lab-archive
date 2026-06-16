package ch05.atom66

interface Base {
    fun f()
}

class Derived1 : Base {
    override fun f() {
        TODO("Not yet implemented")
    }
    fun g() {

    }
}

class Derived2 : Base {
    override fun f() {
        TODO("Not yet implemented")
    }
    fun h() {

    }
}

fun main() {
    val b1: Base = Derived1()
    if (b1 is Derived1) {
        b1.g()
    }
    b1.f()

    val b2: Base = Derived2()
    b2.f()
//    b2.h()
}