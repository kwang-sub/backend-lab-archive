package ch05.atom72

class WithCompanion {
    companion object {
        val i = 3
        fun f() = i * 3
    }

    fun g() = i + f()
}

fun WithCompanion.Companion.h() = f() * i

fun main() {
    val wc = WithCompanion()
    println(wc.g())
    println(WithCompanion.i)
    println(WithCompanion.f())
    println(WithCompanion.h())
}