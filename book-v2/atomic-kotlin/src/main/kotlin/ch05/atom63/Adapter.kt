package ch05.atom63

class MyClass {
    fun g() = println("g")
    fun h() = println("h")
}

fun useMyClass(mc: MyClass) {
    mc.g()
    mc.h()
}

class MyClassAdaptedForLib : LibType {
    val field = MyClass()
    override fun f1() {
        field.h()
    }

    override fun f2() {
        field.g()
    }
}

fun main() {
    val mc = MyClassAdaptedForLib()
    utility1(mc)
    utility2(mc)
    useMyClass(mc.field)
}